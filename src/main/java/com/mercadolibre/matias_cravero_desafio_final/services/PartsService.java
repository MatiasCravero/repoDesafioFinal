package com.mercadolibre.matias_cravero_desafio_final.services;

import com.mercadolibre.matias_cravero_desafio_final.dto.NewPartDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.PartResponseDto;

public interface PartsService {
    PartResponseDto getParts(String queryType, String date, String order) throws Exception;
    Integer updateStock(String partCode, Integer quantity);
    NewPartDto createPart(NewPartDto newPart);
}
