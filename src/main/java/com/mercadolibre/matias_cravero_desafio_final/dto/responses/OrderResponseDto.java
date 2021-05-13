package com.mercadolibre.matias_cravero_desafio_final.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.matias_cravero_desafio_final.dto.OrderDto;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    @JsonProperty("dealerNumber")
    private Long dealerNumber;
    private List<OrderDto> orders;

}
