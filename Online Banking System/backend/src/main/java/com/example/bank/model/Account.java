package com.example.bank.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    public Account() {}
    public Account(String accountNumber, User owner){ this.accountNumber = accountNumber; this.owner = owner; }
    // getters and setters...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.accountNumber=accountNumber;}
    public java.math.BigDecimal getBalance(){return balance;}
    public void setBalance(java.math.BigDecimal balance){this.balance=balance;}
    public User getOwner(){return owner;}
    public void setOwner(User owner){this.owner=owner;}
}
