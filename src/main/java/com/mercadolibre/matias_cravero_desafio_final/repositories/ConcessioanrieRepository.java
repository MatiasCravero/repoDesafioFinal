package com.mercadolibre.matias_cravero_desafio_final.repositories;

import com.mercadolibre.matias_cravero_desafio_final.models.Concessionarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcessioanrieRepository extends JpaRepository<Concessionarie, Long> {
}
