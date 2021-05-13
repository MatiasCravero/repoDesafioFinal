package com.mercadolibre.matias_cravero_desafio_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    @JsonProperty("partCode")
    private String partCode;
    @JsonProperty("description")
    private String description;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("reason")
    private String reason;

}
