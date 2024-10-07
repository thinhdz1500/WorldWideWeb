package com.thinne.backend.business.Bean;

import com.thinne.backend.business.ProductPriceLocal;
import com.thinne.backend.data.entities.ProductPrice;
import com.thinne.backend.data.repositories.ProductPriceRespository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class ProductPriceBean implements ProductPriceLocal {
    @Inject
    private ProductPriceRespository productPriceRespository;
    @Override
    public void add(ProductPrice productPrice) {
        productPriceRespository.save(productPrice);
    }

    @Override
    public void update(ProductPrice productPrice) {
//        productPriceRespository.save(productPrice);
    }

    @Override
    public void delete(ProductPrice productPrice) {
        productPriceRespository.delete(productPrice);
    }

    @Override
    public ProductPrice getProductPrice(int id) {
        return null;
    }

    @Override
    public ProductPrice getProductPriceByProductId(int productId) {
        return productPriceRespository.findByProduct(productId);
    }

}
