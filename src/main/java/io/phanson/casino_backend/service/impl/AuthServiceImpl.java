package io.phanson.casino_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.phanson.casino_backend.dto.request.AccountLoginRequest;
import io.phanson.casino_backend.dto.request.UserAddRequest;
import io.phanson.casino_backend.dto.response.AccountLoginResponse;
import io.phanson.casino_backend.dto.response.UserAddResponse;
import io.phanson.casino_backend.entity.Account;
import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.entity.UserRole;
import io.phanson.casino_backend.entity.VerificationToken;
import io.phanson.casino_backend.mapper.AccountMapper;
import io.phanson.casino_backend.mapper.UserMapper;
import io.phanson.casino_backend.repository.AccountRepo;
import io.phanson.casino_backend.repository.UserRepo;
import io.phanson.casino_backend.service.AccountService;
import io.phanson.casino_backend.service.AuthService;
import io.phanson.casino_backend.service.UserRoleService;
import io.phanson.casino_backend.service.UserService;
import io.phanson.casino_backend.service.VerificationTokenService;
import jakarta.persistence.EntityNotFoundException;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Override
    public AccountLoginResponse login(AccountLoginRequest dto) throws Exception {
        Account acc = accountRepo.findByUsername(dto.getUsername());

        if (acc == null)
            throw new EntityNotFoundException();

        if (acc.getPassword().equals(dto.getPassword())) {
            AccountLoginResponse resp = accountMapper.toAccountLoginResponse(acc);

            return resp;

        } else {
            throw new Exception();
        }
    }

    @Override
    @Transactional
    public UserAddResponse register(UserAddRequest dto) throws Exception {
        Account newAcc = accountService.createAccount(dto.getUsername(), dto.getPassword());
        User newUser = userService.createUser(dto);
        newUser.setAccount(newAcc);

        dto.getRoleName().stream().forEach(roleName -> {
            try {
                UserRole newUserRole = userRoleService.addUserRole(newUser.getId(), roleName);
                newUser.addRole(newUserRole);

            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        });

        VerificationToken token = verificationTokenService.createNewVerificationToken(newUser);
        newUser.setVerificationToken(token);
        newUser.setVerified(false);

        User savedUser = userRepo.save(newUser);

        return userMapper.toUserAddResponse(savedUser);

    }

}
