package io.phanson.casino_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.phanson.casino_backend.dto.request.AccountLoginRequest;
import io.phanson.casino_backend.dto.request.TokenVerifyRequest;
import io.phanson.casino_backend.dto.request.UserAddRequest;
import io.phanson.casino_backend.dto.response.AccountLoginResponse;
import io.phanson.casino_backend.dto.response.UserAddResponse;
import io.phanson.casino_backend.service.AuthService;
import io.phanson.casino_backend.service.VerificationTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private VerificationTokenService tokenService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AccountLoginResponse> login(@RequestBody AccountLoginRequest dto) throws Exception {
        return ResponseEntity.ok().body(authService.login(dto));
    }

    @PreAuthorize("ADMIN")
    @PostMapping("/register")
    public ResponseEntity<UserAddResponse> register(@RequestBody UserAddRequest dto) throws Exception {
        return ResponseEntity.ok().body(authService.register(dto));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody TokenVerifyRequest dto) {
        try {
            return ResponseEntity.ok().body(tokenService.isValidToken(dto) ? "Valid" : "Invalid");
        } catch (Exception e) {
            return ResponseEntity.ok().body("Expired Token");
        }
    }

    

}
