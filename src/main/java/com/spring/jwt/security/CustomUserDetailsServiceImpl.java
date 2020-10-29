package com.spring.jwt.security;

import com.spring.jwt.model.User;
import com.spring.jwt.model.ids.UserId;
import com.spring.jwt.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsernameAndProvider(String username, String provider) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndProvider_Name(username,provider)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username not found for provider, username=%s, provider=%s",
                                username, provider)));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public UserDetails loadUserById(UserId id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));

        return UserDetailsImpl.build(user);
    }
}