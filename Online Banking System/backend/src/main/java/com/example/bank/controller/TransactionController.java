package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tx")
public class TransactionController {
    private final TransactionService txService;
    public TransactionController(TransactionService txService){ this.txService = txService; }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestBody Map<String,String> body){
        String from = body.get("from");
        String to = body.get("to");
        BigDecimal amount = new BigDecimal(body.get("amount"));
        return txService.transfer(from, to, amount);
    }

    @GetMapping("/history/{accNo}")
    public List<Transaction> history(@PathVariable String accNo){
        return txService.history(accNo);
    }
}
