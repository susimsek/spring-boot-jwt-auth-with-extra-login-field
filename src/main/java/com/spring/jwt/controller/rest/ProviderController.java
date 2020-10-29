package com.spring.jwt.controller.rest;


import com.spring.jwt.model.Provider;
import com.spring.jwt.model.User;
import com.spring.jwt.payload.request.ProviderCreateRequest;
import com.spring.jwt.payload.request.UserCreateRequest;
import com.spring.jwt.service.ProviderService;
import com.spring.jwt.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@RequestMapping("/versions/1")
public class ProviderController {

    ProviderService providerService;

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a Provider",response = Provider.class)
    @PostMapping("/providers")
    @ResponseStatus(HttpStatus.CREATED)
    public Provider createProvider(@Valid @RequestBody ProviderCreateRequest providerCreateRequest) {
        return providerService.createProvider(providerCreateRequest);
    }

}
