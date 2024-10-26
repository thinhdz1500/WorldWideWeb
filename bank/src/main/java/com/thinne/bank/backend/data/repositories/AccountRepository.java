package com.thinne.bank.backend.data.repositories;

import com.thinne.bank.backend.data.entities.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AccountRepository {
    @PersistenceContext(unitName = "thinne")
    private EntityManager em;

    public void save(Account account) {
        em.persist(account);
    }
    public List<Account> findAll(){
        TypedQuery<Account> query = em.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }
    public List<Account> getAccountByBalanceBetween(double start, double end){
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.amount between :start and :end", Account.class);
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    };
    public List<Account> getAccountByAddress(String address){
        TypedQuery<Account> query = em.createQuery("SELECT a from  Account a where a.address = :address", Account.class);
        query.setParameter("address", address);
        return query.getResultList();
    }
    public List<Account> getAccountByName(String name){
        TypedQuery<Account> query = em.createQuery("SELECT a from  Account a where a.name = :name", Account.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
    public List<Account> getAccountByAmount(Double amount){
        TypedQuery<Account> query = em.createQuery("SELECT a from  Account a where a.amount = :amount", Account.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

}
