package com.spring.jwt;

import com.spring.jwt.model.ids.UserId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringBootJwtAuthWithExtraLoginFieldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtAuthWithExtraLoginFieldApplication.class, args);
    }

}
