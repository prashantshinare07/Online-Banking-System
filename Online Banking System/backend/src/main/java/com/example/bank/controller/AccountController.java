package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService){ this.accountService = accountService; }

    @PostMapping("/create")
    public Account create(Authentication auth){
        String username = auth.getName();
        return accountService.createAccount(username);
    }

    @GetMapping("/{accNo}")
    public Account get(@PathVariable String accNo){ return accountService.findByAccountNumber(accNo).orElseThrow(); }
}
