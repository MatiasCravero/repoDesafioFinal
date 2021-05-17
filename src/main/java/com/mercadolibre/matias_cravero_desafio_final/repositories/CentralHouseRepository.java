package com.mercadolibre.matias_cravero_desafio_final.repositories;

import com.mercadolibre.matias_cravero_desafio_final.models.CentralHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentralHouseRepository extends JpaRepository<CentralHouse, Long> {
    Optional<CentralHouse> findByCountryEquals(String country);
}
