package com.spring.jwt.service;

import com.spring.jwt.exception.provider.ProviderNotFoundException;
import com.spring.jwt.exception.role.RoleNotFoundException;
import com.spring.jwt.exception.user.UsernameAlreadyExistsException;
import com.spring.jwt.model.Provider;
import com.spring.jwt.model.Role;
import com.spring.jwt.model.User;
import com.spring.jwt.model.enumerated.RoleName;
import com.spring.jwt.model.ids.UserId;
import com.spring.jwt.payload.request.LoginRequest;
import com.spring.jwt.payload.request.UserCreateRequest;
import com.spring.jwt.payload.response.JwtResponse;
import com.spring.jwt.repository.ProviderRepository;
import com.spring.jwt.repository.RoleRepository;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.security.CustomAuthenticationToken;
import com.spring.jwt.security.UserDetailsImpl;
import com.spring.jwt.security.util.JwtTokenUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    AuthenticationManager authenticationManager;
    JwtTokenUtil jwtTokenUtil;
    UserRepository userRepository;
    RoleRepository roleRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    ProviderRepository providerRepository;


    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new CustomAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(),loginRequest.getProvider()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateJwtToken(userPrincipal);

        return new JwtResponse(jwt);
    }


    @Override
    public User createUser(UserCreateRequest userCreateRequest) {

        if (userRepository.existsByUsernameAndProvider_Name(userCreateRequest.getUsername(),userCreateRequest.getProvider())) {
            throw new UsernameAlreadyExistsException();
        }

        User user=modelMapper.map(userCreateRequest, User.class);
        if(user.getPassword()!=null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(userCreateRequest.getProvider()==null){
           userCreateRequest.setProvider("local");
        }

        Provider provider = providerRepository.findByName(userCreateRequest.getProvider())
                .orElseThrow(() -> new ProviderNotFoundException());

        user.setId(new UserId(UUID.randomUUID().toString(),provider.getId()));

        user.setProvider(provider);

        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException());

        user.setRole(role);


        user=userRepository.save(user);
        return user;
    }
}
