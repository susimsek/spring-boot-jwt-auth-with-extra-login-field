package com.spring.jwt.service;

import com.spring.jwt.model.Product;
import com.spring.jwt.payload.request.ProductCreateRequest;

public interface ProductService {

    Product createProduct(ProductCreateRequest productCreateRequest);
    void deleteProduct(String id);
}
