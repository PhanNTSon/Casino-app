package io.phanson.casino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.phanson.casino_backend.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    public Role findByRoleName(String roleName);
}
