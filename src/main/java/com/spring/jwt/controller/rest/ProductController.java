package com.spring.jwt.controller.rest;


import com.spring.jwt.model.Product;
import com.spring.jwt.payload.request.ProductCreateRequest;
import com.spring.jwt.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
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
}
