package io.phanson.casino_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SystemLogs", schema = "dbo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SystemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Actor", length = 255, nullable = false)
    private String actor;

    @Column(name = "Action", length = 255, nullable = false)
    private String action;

    @Column(name = "Target", length = 255, nullable = false)
    private String target;

    @Column(name = "Detail", length = 255, nullable = false)
    private String detail;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
