package com.spring.jwt.model.enumerated;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum  RoleName {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    @NonNull String value;
}
