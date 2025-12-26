### B1: Chọn image base để thu về các thư viện trong pom.xml
### Các thành phần cần thiết: fodler .mvn, file pom.xml, file mvnw vào folder /build
FROM eclipse-temurin:21-jdk-jammy AS deps

WORKDIR /build

COPY pom.xml pom.xml
COPY .mvn/ .mvn/
COPY --chmod=0755 mvnw mvnw

RUN ./mvnw dependency:go-offline -DskipTests

### B2: Copy toàn bộ source code vào image và build thành file Jar
FROM deps AS package

WORKDIR /build
COPY ./src src/

RUN ./mvnw package -DskipTests

### B3: Giải nén file Jar ra thành các lớp gồm: dependencies, spring-boot-loader, snapshot-dependencies, application
### Sử dụng jarmode=layertools để tách các lớp này ra rồi nhét vào folder target/extracted
FROM package AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

### B4: Tạo image cuối cùng để chạy ứng dụng
FROM eclipse-temurin:21-jdk-jammy AS final
WORKDIR /app

# BẢO MẬT: Tạo một người dùng 'appuser' không có quyền quản trị để chạy app.
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

### Di chuyển các lớp đã tách từ image 'extract' sang image cuối cùng
COPY --from=extract /build/target/extracted/dependencies/ ./
COPY --from=extract /build/target/extracted/spring-boot-loader/ ./
COPY --from=extract /build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract /build/target/extracted/application/ ./

### Cổng ứng dụng sẽ chạy trên cổng 8080
EXPOSE 8080

ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]