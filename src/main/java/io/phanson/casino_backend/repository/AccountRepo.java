package io.phanson.casino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.phanson.casino_backend.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
    
    public Account findByUsername(String username);
}
