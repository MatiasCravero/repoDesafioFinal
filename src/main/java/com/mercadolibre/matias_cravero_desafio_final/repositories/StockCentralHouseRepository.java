package com.mercadolibre.matias_cravero_desafio_final.repositories;

import com.mercadolibre.matias_cravero_desafio_final.models.StockCentralHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockCentralHouseRepository extends JpaRepository<StockCentralHouse, Long> {
    StockCentralHouse findByPartIdEqualsAndCentralHouseIdEquals(Long partId, Long centralHouseId);
    List<StockCentralHouse> findByCentralHouseId(Long centralHouseId);
}
