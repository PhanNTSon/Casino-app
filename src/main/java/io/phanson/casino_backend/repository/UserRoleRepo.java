package io.phanson.casino_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.phanson.casino_backend.entity.UserRole;
import io.phanson.casino_backend.entity.UserRoleId;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {
    public Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
}
