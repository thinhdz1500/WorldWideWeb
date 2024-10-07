package com.thinne.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.google.gson.Gson;
// import com.thinne.backend.business.Bean.ProductBean;
import com.thinne.frontend.dtos.ProductDTO;
//import com.thinne.backend.data.entities.Product;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;

import java.util.List;

public class ProductModel {
    // private ProductBean productBean;
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
            String errorMessage = "Error fetching data from path: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public List<ProductDTO> getAllProducts() {
        return executeRequest("", new TypeReference<List<ProductDTO>>() {});
    }

    public ProductDTO getProductById(int id) {
        return executeRequest("/" + id, ProductDTO.class);
    }
//    }
    public Request addProduct(ProductDTO productDTO) {
        // Create a new client
        try (Client client = ClientBuilder.newClient()) {
            // Define the target URI for adding a product
            WebTarget target = client.target(BASED_URI).path("");
            // Send the POST request with the productDTO as JSON
            return target
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(productDTO, MediaType.APPLICATION_JSON), Request.class);
        } catch (Exception e) {
            String errorMessage = "Error adding product: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }

    }//type conversion

    public Response deleteProduct(int id) {
        try (Client client = ClientBuilder.newClient()) {
            // Định nghĩa URI cho việc xóa sản phẩm
            WebTarget target = client.target(BASED_URI).path("/" + id);
            // Gửi yêu cầu DELETE
            return target.request(MediaType.APPLICATION_JSON).delete();
        } catch (Exception e) {
            String errorMessage = "Error deleting product: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    @FunctionalInterface
    private interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }
}
