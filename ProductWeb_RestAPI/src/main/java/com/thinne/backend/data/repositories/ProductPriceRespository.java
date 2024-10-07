package com.thinne.backend.data.repositories;

import com.thinne.backend.data.entities.ProductPrice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProductPriceRespository {
    @PersistenceContext(unitName = "teo_pu")
    private EntityManager em;

    public void save(ProductPrice productPrice) {
        em.persist(productPrice);
    }
    public ProductPrice findByid(int id) {
        return em.find(ProductPrice.class, id);
    }
    public List<ProductPrice> findAll() {
        return em.createQuery("from ProductPrice", ProductPrice.class).getResultList();
    }
}
