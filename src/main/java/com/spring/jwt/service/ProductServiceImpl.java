package com.spring.jwt.service;

import com.spring.jwt.exception.production.ProductAlreadyExistsException;
import com.spring.jwt.exception.provider.ProviderNotFoundException;
import com.spring.jwt.model.Product;
import com.spring.jwt.payload.request.ProductCreateRequest;
import com.spring.jwt.repository.ProductRepository;
import com.spring.jwt.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Page<Product> listProduct(String search,Pageable page) {
        if(search==null){
            return productRepository.findAll(page);
        }
        Node rootNode = new RSQLParser().parse(search);
        Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
        return productRepository.findAll(spec,page);
    }
}
