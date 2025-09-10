package com.example.bank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Transaction() {}
    public Transaction(String fromAccount, String toAccount, BigDecimal amount){
        this.fromAccount = fromAccount; this.toAccount = toAccount; this.amount = amount;
    }
    // getters and setters...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getFromAccount(){return fromAccount;}
    public void setFromAccount(String fromAccount){this.fromAccount=fromAccount;}
    public String getToAccount(){return toAccount;}
    public void setToAccount(String toAccount){this.toAccount=toAccount;}
    public java.math.BigDecimal getAmount(){return amount;}
    public void setAmount(java.math.BigDecimal amount){this.amount=amount;}
    public LocalDateTime getTimestamp(){return timestamp;}
    public void setTimestamp(LocalDateTime timestamp){this.timestamp=timestamp;}
}
