package com.thinne.bank.backend.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "account")

public class Account {
    @Id
    @Size(max = 10)
    @Column(name = "account_number", nullable = false, length = 10)
    private String accountNumber;

    @Size(max = 155)
    @Column(name = "NAME", length = 155)
    private String name;

    @Column(name = "card_number")
    private Integer cardNumber;

    @Size(max = 150)
    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "amount")
    private Double amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}