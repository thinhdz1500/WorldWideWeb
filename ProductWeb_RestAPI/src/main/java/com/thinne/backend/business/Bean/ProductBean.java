package com.thinne.backend.business.Bean;

import com.thinne.backend.business.ProductLocal;
import com.thinne.backend.data.entities.Product;
import com.thinne.backend.data.repositories.ProductRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


import java.util.List;

@Stateless
public class ProductBean implements ProductLocal {
    @Inject
    private ProductRepository productRepository;

    @Override
    public void add(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
