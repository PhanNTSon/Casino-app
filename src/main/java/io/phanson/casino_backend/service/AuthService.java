package io.phanson.casino_backend.service;

import org.springframework.stereotype.Service;

import io.phanson.casino_backend.dto.request.AccountLoginRequest;
import io.phanson.casino_backend.dto.request.UserAddRequest;
import io.phanson.casino_backend.dto.response.AccountLoginResponse;
import io.phanson.casino_backend.dto.response.UserAddResponse;


@Service
public interface AuthService {

    public AccountLoginResponse login (AccountLoginRequest dto) throws Exception;

    public UserAddResponse register (UserAddRequest dto) throws Exception;
}
