package io.phanson.casino_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.phanson.casino_backend.entity.Account;
import io.phanson.casino_backend.repository.AccountRepo;
import io.phanson.casino_backend.service.AccountService;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account updateAccount(Account account) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    @Transactional
    public Account createAccount(String username, String password) throws Exception {
        if (accountRepo.findByUsername(username) != null)
            throw new IllegalArgumentException();

        Account newAcc = new Account();
        newAcc.setUsername(username);
        newAcc.setPassword(password);
        newAcc.setStatus("Active");

        Account savedAcc = accountRepo.save(newAcc);

        return savedAcc;
    }

}
