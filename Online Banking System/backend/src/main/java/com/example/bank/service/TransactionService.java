package com.example.bank.service;

import com.example.bank.model.*;
import com.example.bank.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository; this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction transfer(String fromAcc, String toAcc, BigDecimal amount){
        Account from = accountRepository.findByAccountNumber(fromAcc).orElseThrow();
        Account to = accountRepository.findByAccountNumber(toAcc).orElseThrow();
        if (from.getBalance().compareTo(amount) < 0) throw new RuntimeException("Insufficient funds");
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(from);
        accountRepository.save(to);
        Transaction tx = new Transaction(fromAcc, toAcc, amount);
        return transactionRepository.save(tx);
    }

    public List<Transaction> history(String accNo){
        return transactionRepository.findByFromAccountOrToAccount(accNo, accNo);
    }
}
