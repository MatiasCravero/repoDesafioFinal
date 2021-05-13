package com.mercadolibre.matias_cravero_desafio_final.unit.util;

import com.mercadolibre.matias_cravero_desafio_final.dto.OrderDetailDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.OrderDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.PartDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.OrderResponseDto;
import com.mercadolibre.matias_cravero_desafio_final.models.OrderDetail;
import com.mercadolibre.matias_cravero_desafio_final.unit.fixtures.OrderFixture;
import com.mercadolibre.matias_cravero_desafio_final.unit.fixtures.PartsFixture;
import com.mercadolibre.matias_cravero_desafio_final.util.MockitoExtension;
import com.mercadolibre.matias_cravero_desafio_final.util.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

    private OrderMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new OrderMapper();
    }

    @Test
    @DisplayName("Map single object")
    void mapSingleOrder() {
        OrderDto expected = OrderFixture.defaultOrderDto1();
        OrderDto actual = mapper.map(OrderFixture.defaultOrder1());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Map an order detail list to dto")
    void mapOrderDetailList() {
        List<OrderDetailDto> expected = OrderFixture.defaultOrderDetailDtoList();
        List<OrderDetailDto> actual = mapper.mapOrderDetails(OrderFixture.defaultOrderDetailList());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Map an order list to dto")
    void mapOrderList() {
        List<OrderDto> expected = OrderFixture.defaultOrderDtoList();
        List<OrderDto> actual = mapper.mapList(OrderFixture.defaultOrderList());
        assertEquals(expected, actual);
    }

}