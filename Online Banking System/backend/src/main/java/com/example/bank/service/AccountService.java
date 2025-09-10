package com.example.bank.service;

import com.example.bank.model.*;
import com.example.bank.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository; this.userRepository = userRepository;
    }

    public Account createAccount(String username){
        User u = userRepository.findByUsername(username).orElseThrow();
        String accNo = "ACCT-" + System.currentTimeMillis();
        Account acc = new Account(accNo, u);
        acc.setBalance(BigDecimal.ZERO);
        return accountRepository.save(acc);
    }

    public Optional<Account> findByAccountNumber(String accNo){ return accountRepository.findByAccountNumber(accNo); }
}
