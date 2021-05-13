package com.mercadolibre.matias_cravero_desafio_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @JsonProperty("orderNumber")
    private Integer orderNumber;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("deliveryDate")
    private String deliveryDate;
    @JsonProperty("daysDelayed")
    private Integer daysDelayed;
    @JsonProperty("deliveryStatus")
    private String deliveryStatus;
    @JsonProperty("orderDetails")
    private List<OrderDetailDto> orderDetails;
    
}
