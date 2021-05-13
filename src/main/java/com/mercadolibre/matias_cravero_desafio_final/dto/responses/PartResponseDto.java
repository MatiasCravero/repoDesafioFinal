package com.mercadolibre.matias_cravero_desafio_final.dto.responses;

import com.mercadolibre.matias_cravero_desafio_final.dto.PartDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PartResponseDto {
    private List<PartDto> parts;
}
