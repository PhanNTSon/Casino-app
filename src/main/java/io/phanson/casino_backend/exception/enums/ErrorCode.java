package io.phanson.casino_backend.exception.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Nhóm Hệ thống & Auth (1000 - 1999)
    INVALID_TOKEN("1001", "Token không hợp lệ hoặc đã bị thay đổi", HttpStatus.UNAUTHORIZED),
    EXPIRE_TOKEN("1002", "Phiên làm việc đã hết hạn", HttpStatus.UNAUTHORIZED),
    INVALID_ROLE("1003", "Bạn không có quyền thực hiện hành động này", HttpStatus.FORBIDDEN),

    // Nhóm Người dùng (2000 - 2999)
    USER_NOT_FOUND("2001", "Người dùng không tồn tại trên hệ thống", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS("2002", "Tên đăng nhập đã được sử dụng", HttpStatus.CONFLICT),
    ACCOUNT_DEACTIVATED("2003", "Tài khoản của bạn đang bị tạm khóa", HttpStatus.FORBIDDEN),

    // Nhóm Giao dịch & Casino (3000 - 3999)
    INVALID_BET_AMOUNT("3001", "Số tiền cược không hợp lệ", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE("3002", "Số dư tài khoản không đủ để thực hiện giao dịch", HttpStatus.PAYMENT_REQUIRED),

    // Nhóm Lỗi không xác định
    UNCATEGORIZED_EXCEPTION("9999", "Lỗi hệ thống chưa xác định", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String mesg;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String mesg, HttpStatus httpStatus) {
        this.code = code;
        this.mesg = mesg;
        this.httpStatus = httpStatus;
    }
}
