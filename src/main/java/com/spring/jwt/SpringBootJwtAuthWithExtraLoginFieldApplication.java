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
        UserId id = new UserId("admin",2L);
        String jwt =Jwts.builder()
                .setSubject((String.valueOf(id)))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()))
                .signWith(SignatureAlgorithm.HS256, "R1BYcTVXVGNDU2JmWHVnZ1lnN0FKeGR3cU1RUU45QXV4SDJONFZ3ckhwS1N0ZjNCYVkzZ0F4RVBSS1UzRENwRw==")
                .compact();
        System.out.println(jwt);
    }

}
