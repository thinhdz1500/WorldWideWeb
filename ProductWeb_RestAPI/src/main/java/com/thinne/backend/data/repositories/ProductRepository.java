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
    public Product findById(int id) {
        return em.find(Product.class, id);
    }
    public List<Product> findAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }
    public void delete(Product product) {
        // Tìm lại thực thể từ EntityManager
        Product managedProduct = em.find(Product.class, product.getId());
        if (managedProduct != null) {
            em.remove(managedProduct);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }
    //....,..
}
