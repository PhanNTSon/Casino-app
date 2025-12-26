package io.phanson.casino_backend.entity;

import java.util.HashSet;
import java.util.Set;

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
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public void addMoney(double money) {
        this.walletBalance += money;
    }

    public void addRole(UserRole userRole) {
        if (this.userRoles == null)
            this.userRoles = new HashSet<>();
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }
}
