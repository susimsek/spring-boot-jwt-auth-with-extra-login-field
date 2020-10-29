package com.spring.jwt.model.ids;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserId implements Serializable {

    @Column(name = "user_id")
    String userId;

    @Column(name = "provider_id")
    Long providerId;

}
