package io.phanson.casino_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.phanson.casino_backend.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>{
    
}
