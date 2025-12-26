package io.phanson.casino_backend.service;

import org.springframework.stereotype.Service;

import io.phanson.casino_backend.entity.UserRole;

@Service
public interface UserRoleService {
    
    public UserRole addUserRole(Long userId, String roleName) throws Exception;

    public void removeUserRole(Long userId, Long roleId) throws Exception;

}
