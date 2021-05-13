package com.mercadolibre.matias_cravero_desafio_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String code;
    private String description;
    @JsonProperty("statusCode")
    private Integer statusCode;
}
