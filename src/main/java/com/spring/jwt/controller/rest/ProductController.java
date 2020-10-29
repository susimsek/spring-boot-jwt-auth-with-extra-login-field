package com.spring.jwt.controller.rest;


import com.spring.jwt.model.Product;
import com.spring.jwt.payload.request.ProductCreateRequest;
import com.spring.jwt.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@RequestMapping("/versions/1")
public class ProductController {

    ProductService productService;

    @ApiOperation(value = "Create a Product",response = Product.class)
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        return productService.createProduct(productCreateRequest);
    }

    @ApiOperation(value = "Delete a Product")
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@NotBlank @PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "List Product", response = Iterable.class )
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> listProduct(Pageable page) {
        return productService.listProduct(page);
    }

}
