package io.phanson.casino_backend.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FullName", length = 255, nullable = false)
    private String fullName;

    @Column(name = "WalletBalance")
    private double walletBalance;

    @Column(name = "Email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "Verified")
    private boolean verified;

    @OneToOne(mappedBy = "user")
    private VerificationToken verificationToken;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "accountId")
    private Account account;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        Assert.hasText(fullName, "Full name cannot be null or empty");
        Assert.isTrue(fullName.length() <= 255, "Full name cannot be more than 255 characters");
        this.fullName = fullName;
    }

    public void setWalletBalance(double walletBalance) {
        Assert.notNull(walletBalance, "Wallet Balance cannot be null");
        Assert.isTrue(walletBalance >= 0, "Wallet Balance cannot be negative");
        this.walletBalance = walletBalance;
    }

    public void setEmail(String email) {
        Assert.hasText(email, "Email cannot be null or empty");
        Assert.isTrue(email.length() <= 255, "Email cannot be more than 255 characters");
        this.email = email;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addMoney(double money) {
        Assert.isTrue(money >= 0, "Money to add cannot be negative");
        this.walletBalance += money;
    }

    public void deductMoney(double money) {
        Assert.isTrue(money >= 0, "Money to deduct cannot be negative");
        Assert.isTrue(this.walletBalance >= money, "Insufficient balance");
        this.walletBalance -= money;
    }

    public void addRole(UserRole userRole) {
        if (this.userRoles == null)
            this.userRoles = new HashSet<>();
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }
}
