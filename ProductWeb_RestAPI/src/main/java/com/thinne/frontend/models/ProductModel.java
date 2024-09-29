package com.thinne.frontend.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinne.frontend.dtos.Product;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.lang.reflect.Type;

public class ProductModel {

    private static final String BASED_URI = "http://localhost:8080/ProductWeb_RestAPI-1.0-SNAPSHOT/api/products";
    private final ObjectMapper mapper = new ObjectMapper();

    private <T> T executeRequest(String path, Class<T> responseType) {
        return executeRequestInternal(path, json -> mapper.readValue(json, responseType));
    }

    private <T> T executeRequest(String path, TypeReference<T> typeReference) {
        return executeRequestInternal(path, json -> mapper.readValue(json, typeReference));
    }

    private <T> T executeRequestInternal(String path, ThrowingFunction<String, T> jsonParser) {
        WebTarget target = null;
        try (Client client = ClientBuilder.newClient()) {
            target = client.target(BASED_URI).path(path);
            String json = target
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            return jsonParser.apply(json);
        } catch (Exception e) {
            String errorMessage = "Error fetching data from path: " + path;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<Product> getAllProducts() {
        return executeRequest("", new TypeReference<List<Product>>() {});
    }

    public Product getProductById(int id) {
        return executeRequest("/" + id, Product.class);
    }

    @FunctionalInterface
    private interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }
}
