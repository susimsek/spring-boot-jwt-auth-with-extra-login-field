package com.spring.jwt.exception.provider;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Error: Provider is already taken!")
public class ProviderAlreadyExistsException extends RuntimeException {

}