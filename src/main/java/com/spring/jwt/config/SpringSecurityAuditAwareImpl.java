package com.spring.jwt.config;

import com.spring.jwt.model.User;
import com.spring.jwt.security.UserDetailsImpl;
import com.sun.security.auth.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class SpringSecurityAuditAwareImpl implements AuditorAware<User> {

    private ModelMapper modelMapper;

    public SpringSecurityAuditAwareImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Optional.ofNullable(modelMapper.map(userPrincipal,User.class));
    }
}