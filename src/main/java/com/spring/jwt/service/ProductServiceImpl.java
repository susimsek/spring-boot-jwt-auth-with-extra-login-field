package com.spring.jwt.service;

import com.spring.jwt.exception.production.ProductAlreadyExistsException;
import com.spring.jwt.exception.provider.ProviderNotFoundException;
import com.spring.jwt.model.Product;
import com.spring.jwt.payload.request.ProductCreateRequest;
import com.spring.jwt.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    ModelMapper modelMapper;

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {

        if (productRepository.existsByName(productCreateRequest.getName())) {
            throw new ProductAlreadyExistsException();
        }
        Product product=modelMapper.map(productCreateRequest, Product.class);

        product=productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProviderNotFoundException());
        productRepository.delete(product);
    }
}
