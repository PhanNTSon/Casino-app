package io.phanson.casino_backend.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRoleId implements Serializable{
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "RoleId")
    private Long roleId;
}
