package com.thinne.bank.backend.resource.accounts;

import com.thinne.bank.backend.buisiness.AccountLocal;
import com.thinne.bank.backend.data.entities.Account;
import com.thinne.bank.backend.dtos.AccountDTO;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/accounts")
public class AccountResource {
    @EJB
    private AccountLocal accountLocal;
    @GET
    public Response getAccounts() {
        List<Account> list = accountLocal.getAll();
        List<AccountDTO> listResult = new ArrayList<>();
        AccountDTO accountDTO = null;
        for (Account ac : list) {
            accountDTO = AccountDTO.builder()
                    .accountNumber(ac.getAccountNumber())
                    .name(ac.getName())
                    .address(ac.getAddress())
                    .amount(ac.getAmount())
                    .cardNumber(ac.getCardNumber())
                    .build();
            listResult.add(accountDTO);
        }

        return Response.ok(listResult).build();
    }
    @GET
    @Path("/balance")
    public Response getAccountByBalance(@QueryParam("start") double start, @QueryParam("end") double end) {
        List<Account> list = accountLocal.getAccountByBalanceBetween(start, end);
        List<AccountDTO> listResult = new ArrayList<>();
        for (Account ac : list) {
            AccountDTO accountDTO = AccountDTO.builder()
                    .accountNumber(ac.getAccountNumber())
                    .name(ac.getName())
                    .address(ac.getAddress())
                    .amount(ac.getAmount())
                    .cardNumber(ac.getCardNumber())
                    .build();
            listResult.add(accountDTO);
        }
        return Response.ok(listResult).build();
    }
    @GET
    @Path("/address")
    public Response getAccountsByAddress(@QueryParam("address") String address) {
        List<Account> list = accountLocal.getAccountByAddress(address);
        List<AccountDTO> listResult = new ArrayList<>();
        for (Account ac : list) {
            AccountDTO accountDTO = AccountDTO.builder()
                    .accountNumber(ac.getAccountNumber())
                    .name(ac.getName())
                    .address(ac.getAddress())
                    .amount(ac.getAmount())
                    .cardNumber(ac.getCardNumber())
                    .build();
            listResult.add(accountDTO);
        }
        return Response.ok(listResult).build();
    }

    @GET
    @Path("/name")
    public Response getAccountsByName(@QueryParam("name") String name) {
        List<Account> list = accountLocal.findByName(name);
        List<AccountDTO> listResult = new ArrayList<>();
        for (Account ac : list) {
            AccountDTO accountDTO = AccountDTO.builder()
                    .accountNumber(ac.getAccountNumber())
                    .name(ac.getName())
                    .address(ac.getAddress())
                    .amount(ac.getAmount())
                    .cardNumber(ac.getCardNumber())
                    .build();
            listResult.add(accountDTO);
        }
        return Response.ok(listResult).build();
    }

    @GET
    @Path("/amount")
    public Response getAccountsByName(@QueryParam("amount") double amount) {
        List<Account> list = accountLocal.findByAmount(amount);
        List<AccountDTO> listResult = new ArrayList<>();
        for (Account ac : list) {
            AccountDTO accountDTO = AccountDTO.builder()
                    .accountNumber(ac.getAccountNumber())
                    .name(ac.getName())
                    .address(ac.getAddress())
                    .amount(ac.getAmount())
                    .cardNumber(ac.getCardNumber())
                    .build();
            listResult.add(accountDTO);
        }
        return Response.ok(listResult).build();
    }
}
