package com.mercadolibre.matias_cravero_desafio_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPartDto {
    @JsonProperty("partCode")
    private String partCode;
    @JsonProperty("description")
    private String description;
    @JsonProperty("message")
    private String message;
}
