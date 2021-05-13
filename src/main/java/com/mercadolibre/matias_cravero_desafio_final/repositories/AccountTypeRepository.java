package com.mercadolibre.matias_cravero_desafio_final.repositories;

import com.mercadolibre.matias_cravero_desafio_final.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    Optional<AccountType> findByNameEquals(String name);
}
