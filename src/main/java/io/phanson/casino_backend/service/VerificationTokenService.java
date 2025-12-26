package io.phanson.casino_backend.service;

import org.springframework.stereotype.Service;

import io.phanson.casino_backend.dto.request.TokenVerifyRequest;
import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.entity.VerificationToken;

@Service
public interface VerificationTokenService {
    
    public VerificationToken createNewVerificationToken(User user);

    public boolean isValidToken(TokenVerifyRequest dto) throws Exception;
}
