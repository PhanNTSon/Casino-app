package io.phanson.casino_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.phanson.casino_backend.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

}
