package com.spring.jwt.config;


import com.spring.jwt.security.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@EnableSwagger2
@Configuration
public class JwtAuhSwaggerConfig {

    private static final String JWT_AUTH = "jwt_auth";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.jwt.controller.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .globalRequestParameters(parameters())
                .ignoredParameterTypes(UserDetailsImpl.class)
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(List.of(securityContext()))
                .directModelSubstitute(Pageable.class,SwaggerPageable.class);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot Jwt Auth With Extra Field REST API")
                .description("\"Provides access to the core features of Spring Boot Jwt Auth With Extra Field Rest Api.\"")
                .version("1.0.0")
                .build();
    }

    private List<RequestParameter> parameters() {
        RequestParameterBuilder mediaTypeBuilder = new RequestParameterBuilder();

        var requestParameter = mediaTypeBuilder
                .name("mediaType")
                .description("Enter media type: xml,yaml or json.")
                .required(false)
                .in("query")
                .query(q -> {
                    q.model(m -> m.scalarModel(ScalarType.STRING));
                    q.defaultValue("json");
                })
                .build();

        List<RequestParameter> params = new ArrayList<>();
        params.add(requestParameter);
        return params;
    }


    private SecurityScheme securityScheme() {
        return new ApiKey(JWT_AUTH, "Authorization", "header");
    }

    private SecurityReference jwtAuthReference() {
        return new SecurityReference(JWT_AUTH, new AuthorizationScope[0]);
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(List.of(jwtAuthReference()))
                .forPaths(PathSelectors.regex("^(?!.*signin).*$"))
                .build();
    }
}
