package com.mercadolibre.matias_cravero_desafio_final.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.matias_cravero_desafio_final.dto.StockPartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPartResponseDto {
    @JsonProperty("parts")
    private List<StockPartDto> parts;

}
