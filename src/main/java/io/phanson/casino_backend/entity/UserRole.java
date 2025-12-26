package io.phanson.casino_backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserRoles", schema = "dbo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRole {

    @EmbeddedId
    private UserRoleId userRoleId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "RoleId")
    private Role role;
}
