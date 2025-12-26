package io.phanson.casino_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.phanson.casino_backend.entity.Role;
import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.entity.UserRole;
import io.phanson.casino_backend.repository.RoleRepo;
import io.phanson.casino_backend.repository.UserRepo;
import io.phanson.casino_backend.repository.UserRoleRepo;
import io.phanson.casino_backend.service.UserRoleService;
import jakarta.persistence.EntityNotFoundException;

@Component
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    @Transactional
    public UserRole addUserRole(Long userId, String roleName) throws Exception {
        User user = userRepo.findById(userId).orElse(null);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        UserRole newUserRole = new UserRole();
        Role role = roleRepo.findByRoleName(roleName);

        newUserRole.setUser(user);
        newUserRole.setRole(role);

        return userRoleRepo.save(newUserRole);

    }

    @Override
    @Transactional
    public void removeUserRole(Long userId, Long roleId) throws Exception {
        UserRole userRole = userRoleRepo.findByUserIdAndRoleId(userId,roleId).orElseThrow(() -> new EntityNotFoundException());
        userRoleRepo.delete(userRole);
    }

}
