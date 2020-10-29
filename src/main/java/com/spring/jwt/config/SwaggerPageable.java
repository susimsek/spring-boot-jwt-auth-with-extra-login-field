package com.spring.jwt.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SwaggerPageable {

    @ApiModelProperty(value = "Number of records per page",
            dataType = "int",
            example = "10"
    )
    private String size;

    @ApiModelProperty(value = "Results page you want to retrieve (0..N)",
            dataType = "int",
            example = "0"
    )
    private String page;

    @ApiModelProperty(
            value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.",
            dataType = "string"
    )
    private String sort;

}
