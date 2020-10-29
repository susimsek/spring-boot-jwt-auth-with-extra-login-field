package com.spring.jwt.service;


import com.spring.jwt.model.User;
import com.spring.jwt.payload.request.LoginRequest;
import com.spring.jwt.payload.request.UserCreateRequest;
import com.spring.jwt.payload.response.JwtResponse;

public interface UserService {

    JwtResponse authenticateUser(LoginRequest loginRequest);
    User createUser(UserCreateRequest userCreateRequest);

}
