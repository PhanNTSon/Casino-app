package io.phanson.casino_backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VerificationTokens", schema = "dbo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VerificationToken {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Token", nullable = false, length = 6)
    private String token;

    @Column(name = "ExpiryDate", nullable = false)
    private LocalDateTime expiryDate;

    @OneToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    public boolean isExpired() {
        return this.expiryDate.isBefore(LocalDateTime.now());
    }
}
