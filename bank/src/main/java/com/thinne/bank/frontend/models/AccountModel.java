package com.thinne.bank.frontend.models;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinne.bank.backend.dtos.AccountDTO;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

public class AccountModel {
    private final String BASED_URI = "http://localhost:8080/bank-1.0-SNAPSHOT/api/accounts";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<AccountDTO> getAllAccounts(){
        try(Client client = ClientBuilder.newClient()){
            WebTarget target = client.target(BASED_URI);
            String json = target.request(MediaType.APPLICATION_JSON).get(String.class);
            return mapper.readValue(json, new TypeReference<List<AccountDTO>>() {});
        } catch (Exception e) {
            String errorMessage = "Error fetching data from path: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
    public List<AccountDTO> getAccountByName(String name){
        try(Client client = ClientBuilder.newClient()){
            WebTarget target = client.target(BASED_URI).path("/name").queryParam("name", name);
            String json = target.request(MediaType.APPLICATION_JSON).get(String.class);
            return mapper.readValue(json, new TypeReference<List<AccountDTO>>() {});
        } catch (Exception e) {
            String errorMessage = "Error fetching data from path: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<AccountDTO> getAccountByAmount(Double amount){
        try(Client client = ClientBuilder.newClient()){
            WebTarget target = client.target(BASED_URI).path("/amount").queryParam("amount", amount);
            String json = target.request(MediaType.APPLICATION_JSON).get(String.class);
            return mapper.readValue(json, new TypeReference<List<AccountDTO>>() {});
        } catch (Exception e) {
            String errorMessage = "Error fetching data from path: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
    public AccountDTO addAccount(AccountDTO account) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URI);
            String json = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(account, MediaType.APPLICATION_JSON), String.class);
            return mapper.readValue(json, AccountDTO.class);
        } catch (Exception e) {
            String errorMessage = "Error adding account: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public boolean deleteAccount(Long id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URI).path("/{id}").resolveTemplate("id", id);
            target.request().delete();
            return true;
        } catch (Exception e) {
            String errorMessage = "Error deleting account: " + e;
            System.out.println(errorMessage);
            return false;
        }
    }

    public AccountDTO updateAccount(AccountDTO account) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URI).path("/{id}").resolveTemplate("id", account.getAccountNumber());
            String json = target.request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(account, MediaType.APPLICATION_JSON), String.class);
            return mapper.readValue(json, AccountDTO.class);
        } catch (Exception e) {
            String errorMessage = "Error updating account: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
