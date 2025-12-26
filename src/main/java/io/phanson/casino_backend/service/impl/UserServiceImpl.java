package io.phanson.casino_backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.phanson.casino_backend.dto.request.UserAddRequest;
import io.phanson.casino_backend.dto.request.UserBetRequest;
import io.phanson.casino_backend.dto.request.UserUpdateRequest;
import io.phanson.casino_backend.dto.response.UserBetResponse;
import io.phanson.casino_backend.dto.response.UserUpdateResponse;
import io.phanson.casino_backend.dto.response.UserViewResponse;
import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.mapper.UserMapper;
import io.phanson.casino_backend.repository.UserRepo;
import io.phanson.casino_backend.service.UserRoleService;
import io.phanson.casino_backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<UserViewResponse> getUserList() {
        List<User> users = userRepo.findAll();

        return users.stream().map(u -> userMapper.toUserViewResponse(u)).toList();
    }

    @Override
    @Transactional
    public UserUpdateResponse updateUser(UserUpdateRequest dto) throws Exception {
        User user = userRepo.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException());

        user.setFullName(dto.getFullName());
        user.setWalletBalance(dto.getWalletBalance());
        user.getAccount().setPassword(dto.getPassword());

        user.getUserRoles().stream().forEach(ur -> {
            try {
                userRoleService.removeUserRole(user.getId(), ur.getRole().getId());
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        });

        user.getUserRoles().clear();

        dto.getRoleNames().stream().forEach(roleName -> {
            try {
                user.getUserRoles().add(userRoleService.addUserRole(dto.getId(), roleName));
            } catch (Exception e) {
                throw new EntityNotFoundException();
            }
        });

        User savedUser = userRepo.save(user);

        return userMapper.toUserUpdateResponse(savedUser);

    }

    @Override
    @Transactional
    public void removeUser(Long userId) throws Exception {
        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException());
        userRepo.delete(user);
    }

    @Override
    @Transactional
    public UserBetResponse bet(UserBetRequest dto) throws Exception {
        User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new EntityNotFoundException());
        user.addMoney(dto.getBetAmount() * dto.getMultiplier());
        User savedUser = userRepo.save(user);

        return userMapper.toUserBetResponse(savedUser);
    }

    @Override
    @Transactional
    public User createUser(UserAddRequest dto) throws Exception {
        User newUser = new User();

        newUser.setFullName(dto.getFullName());
        newUser.setEmail(dto.getEmail());
        newUser.setWalletBalance(0);
        
        return userRepo.save(newUser);
    }

}
