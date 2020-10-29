package com.spring.jwt.controller.rest;


import com.spring.jwt.model.User;
import com.spring.jwt.payload.request.UserCreateRequest;
import com.spring.jwt.security.CurrentUser;
import com.spring.jwt.security.UserDetailsImpl;
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
public class UserController {

    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a User",response = User.class)
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

}
