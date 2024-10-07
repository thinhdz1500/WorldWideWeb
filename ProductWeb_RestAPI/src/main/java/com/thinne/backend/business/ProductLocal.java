package com.thinne.backend.business;

import com.thinne.backend.data.entities.Product;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ProductLocal {
    void add(Product product);
//    void update(Product product);
    void delete(Product product);
    Product getProduct(int id);
    List<Product> getProducts();
}
