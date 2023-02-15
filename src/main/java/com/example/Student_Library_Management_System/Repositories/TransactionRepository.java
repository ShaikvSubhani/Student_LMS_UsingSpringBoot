package com.example.Student_Library_Management_System.Repositories;

import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
