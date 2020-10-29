package com.spring.jwt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.jwt.model.audit.DateAudit;
import com.spring.jwt.model.ids.UserId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(	name = "users")
public class User{

    @EmbeddedId
    UserId id;

    @NotBlank
    @Size(max = 100)
    String username;

    @JsonIgnore
    @Size(max = 120)
    String password;

    @NotBlank
    @Size(max = 50)
    @Email
    String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

    @ManyToOne
    @MapsId("providerId")
    Provider provider;
}
