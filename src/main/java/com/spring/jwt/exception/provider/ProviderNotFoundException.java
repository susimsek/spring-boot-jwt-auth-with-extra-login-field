package com.spring.jwt.exception.provider;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Error: Provider is not found.")
public class ProviderNotFoundException extends RuntimeException {

}
