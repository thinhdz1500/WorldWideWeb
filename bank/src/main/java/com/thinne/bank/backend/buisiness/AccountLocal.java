package com.thinne.bank.backend.buisiness;

import com.thinne.bank.backend.data.entities.Account;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AccountLocal {
    void add (Account Account);
    List<Account> getAll();
    List<Account> getAccountByBalanceBetween(double start,double end);
    List<Account> getAccountByAddress(String address);
    List<Account> findByName(String name);
    List<Account> findByAmount(Double amount);
}
