package com.spring.jwt.service;

import com.spring.jwt.model.Product;
import com.spring.jwt.payload.request.ProductCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    Product createProduct(ProductCreateRequest productCreateRequest);
    void deleteProduct(String id);
    Page<Product> listProduct(Pageable page);
}
