package com.thinne.bank.backend.buisiness.bean;

import com.thinne.bank.backend.buisiness.AccountLocal;
import com.thinne.bank.backend.data.entities.Account;
import com.thinne.bank.backend.data.repositories.AccountRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
@Stateless
public class AccountBean implements AccountLocal {
    @Inject
    private AccountRepository accountRepository;
    @Override
    public void add(Account Account) {
        accountRepository.save(Account);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAccountByBalanceBetween(double start, double end) {
        return accountRepository.getAccountByBalanceBetween(start,end);
    }


    @Override
    public List<Account> getAccountByAddress(String address) {
        return accountRepository.getAccountByAddress(address);
    }

    @Override
    public List<Account> findByName(String name) {
        return accountRepository.getAccountByName(name);
    }

    @Override
    public List<Account> findByAmount(Double amount) {
        return accountRepository.getAccountByAmount(amount);
    }
}
