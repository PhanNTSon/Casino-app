package io.phanson.casino_backend.service;


import org.springframework.stereotype.Service;

import io.phanson.casino_backend.entity.Account;


@Service
public interface AccountService {
    
    public Account createAccount(String username, String password) throws Exception;

    public Account updateAccount(Account account) throws Exception;

    

}
