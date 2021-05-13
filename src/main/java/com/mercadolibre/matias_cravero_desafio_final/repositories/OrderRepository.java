package com.mercadolibre.matias_cravero_desafio_final.repositories;

import com.mercadolibre.matias_cravero_desafio_final.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByConcessionarieIdEqualsAndDeliveryStatusIdEquals(Long concessionary_id, Long delivery_id);
    List<Order> findOrderByConcessionarieIdEquals(Long concessionarie_id);
    List<Order> findOrderByConcessionarieIdEqualsAndCentralHouseIdEqualsAndOrderNumberCMEquals(Long concessionaryId, Long centralHouseId, Integer orderNumberCM);
    Optional<Order> findByOrderNumberCMEquals(Integer orderNumberCM);
}
