package com.spring.jwt.exception.production;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Error: Product is already taken!")
public class ProductAlreadyExistsException extends RuntimeException {

}