package io.phanson.casino_backend.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.phanson.casino_backend.dto.request.TokenVerifyRequest;
import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.entity.VerificationToken;
import io.phanson.casino_backend.exception.CustomizeException.VerifyTokenExpireException;
import io.phanson.casino_backend.repository.UserRepo;
import io.phanson.casino_backend.repository.VerificationTokenRepo;
import io.phanson.casino_backend.service.VerificationTokenService;
import io.phanson.casino_backend.utils.Generator;
import jakarta.persistence.EntityNotFoundException;

@Component
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    private VerificationTokenRepo tokenRepository;

    @Autowired
    private UserRepo userRepository;

    /**
     * Create, save to DB and return a verification token for an user.
     * 
     * @param user
     * @return
     */
    @Transactional
    public VerificationToken createNewVerificationToken(User user) {
        VerificationToken newToken = new VerificationToken();
        newToken.setToken(Generator.generateVerificationCode());
        newToken.setUser(user);
        newToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        tokenRepository.save(newToken);

        return newToken;
    }

    @Transactional
    public boolean isValidToken(TokenVerifyRequest dto) throws Exception {
        User curU = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new EntityNotFoundException());

        if (curU.isVerified())
            throw new Exception("User already verified");

        VerificationToken curUserToken = curU.getVerificationToken();

        if (curUserToken.isExpired()) {
            throw new VerifyTokenExpireException();
        }

        if (curUserToken.getToken().equals(dto.getToken())) {
            curU.setVerified(true);
            curU.setVerificationToken(null);
            userRepository.save(curU);
            return true;
        }

        return false;
    }

}
