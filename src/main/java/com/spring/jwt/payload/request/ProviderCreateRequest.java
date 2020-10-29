package com.spring.jwt.payload.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "Provider Create Model")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProviderCreateRequest {

    @ApiModelProperty(position = 0,required = true)
    @NotBlank
    @Size(max = 100)
    String name;
}