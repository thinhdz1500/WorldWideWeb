package com.thinne.backend.data.repositories;

import com.thinne.backend.data.entities.Product;
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

    public ProductPrice findByProduct(int productId) {
        List<ProductPrice> results = em.createNamedQuery("Product_price.findByProductId", ProductPrice.class)
                .setParameter("id", productId)
                .getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
    public void delete(ProductPrice productPrice) {
        // Tìm lại thực thể từ EntityManager
        ProductPrice managedProduct = em.find(ProductPrice.class, productPrice.getId());
        if (managedProduct != null) {
            em.remove(managedProduct);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }
}
