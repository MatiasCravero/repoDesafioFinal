package com.mercadolibre.matias_cravero_desafio_final.unit.util;

import com.mercadolibre.matias_cravero_desafio_final.dto.PartDto;
import com.mercadolibre.matias_cravero_desafio_final.models.Order;
import com.mercadolibre.matias_cravero_desafio_final.repositories.PartRepository;
import com.mercadolibre.matias_cravero_desafio_final.unit.fixtures.OrderFixture;
import com.mercadolibre.matias_cravero_desafio_final.unit.fixtures.PartsFixture;
import com.mercadolibre.matias_cravero_desafio_final.util.MockitoExtension;
import com.mercadolibre.matias_cravero_desafio_final.util.OrderUtil;
import com.mercadolibre.matias_cravero_desafio_final.util.PartMapper;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;


@ExtendWith(MockitoExtension.class)
class OrderUtilTest {

    private OrderUtil orderUtil;
    private PartRepository repoPartsMock;

    @BeforeEach
    void setUp() {
        repoPartsMock = Mockito.mock(PartRepository.class);
        orderUtil = new OrderUtil(repoPartsMock);
    }

    @Test
    @DisplayName("Generate order")
    void generateOrder() {
        Order expected = OrderFixture.defaultOrder();
        Order actual = orderUtil.generateOrder(OrderFixture.defaultConcessionarie(),
                OrderFixture.defaultShippingType(), OrderFixture.defaultCentralHouse(),
                OrderFixture.defaultDeliveryStatus(), 3);
        Assert.assertEquals(expected, actual);
    }




}