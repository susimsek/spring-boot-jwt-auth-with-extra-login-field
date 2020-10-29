package com.spring.jwt.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    @Getter
    private String provider;

    public CustomAuthenticationToken(Object principal, Object credentials, String provider) {
        super(principal, credentials);
        this.provider = provider;
        super.setAuthenticated(false);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, String provider,
        Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.provider = provider;
    }

}