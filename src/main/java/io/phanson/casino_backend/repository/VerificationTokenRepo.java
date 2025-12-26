package io.phanson.casino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.entity.VerificationToken;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    public void deleteByUser(User user);

    public VerificationToken findByUser(User user);
}
