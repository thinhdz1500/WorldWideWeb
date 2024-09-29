package com.thinne.backend.data.repositories;

import com.thinne.backend.data.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProductRepository {
    @PersistenceContext(unitName = "teo_pu")
    private EntityManager em;

    public void save(Product product) {
        em.persist(product);
    }
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }
    public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }
    public void delete(Product product) {
        em.remove(product);
    }
    //....,..
}
