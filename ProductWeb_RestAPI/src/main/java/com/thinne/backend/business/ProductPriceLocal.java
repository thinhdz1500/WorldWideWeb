package com.thinne.backend.business;

import com.thinne.backend.data.entities.ProductPrice;

public interface ProductPriceLocal {
    void add(ProductPrice productPrice);
    void update(ProductPrice productPrice);
    void delete(ProductPrice productPrice);
    ProductPrice getProductPrice(int id);

}
