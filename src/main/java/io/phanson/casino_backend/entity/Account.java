package io.phanson.casino_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Accounts", schema = "dbo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long accountId;

    @Column(name = "Username", length = 255, nullable = false, unique = true)
    private String username;

    @Column(name = "Password", length = 255, nullable = false)
    private String password;

    @Column(name = "Status", length = 50)
    private String status;

    @OneToOne(mappedBy = "account")
    private User user;

    public void setAccountId(Long accountId) {
        if (accountId < 0)
            throw new IllegalArgumentException("Invalid Id");
        this.accountId = accountId;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty())
            throw new IllegalArgumentException("Username cannot be null or empty");
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password cannot be null or empty");
        this.password = password;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty())
            throw new IllegalArgumentException("Status cannot be null or empty");
        if (!status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("deactive"))
            throw new IllegalArgumentException("Status must be ACTIVE or DEACTIVE");

        this.status = status;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
    }

}
