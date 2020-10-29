package com.spring.jwt.payload.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "Login Model")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

	@ApiModelProperty(position = 0,required = true, example = "local")
	@NotBlank
	String provider;

	@ApiModelProperty(position = 1,required = true)
	@NotBlank
	String username;

	@ApiModelProperty(position = 2,required = true)
	@NotBlank
	String password;

}