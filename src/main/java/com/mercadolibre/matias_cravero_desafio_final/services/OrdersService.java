package com.mercadolibre.matias_cravero_desafio_final.services;

import com.mercadolibre.matias_cravero_desafio_final.dto.OrderRequestDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.BasicResponseDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.GenerateOrderResponseDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.OrderResponseDto;
import com.mercadolibre.matias_cravero_desafio_final.dto.responses.OrderStatusDto;

public interface OrdersService {

    OrderResponseDto getOrders(Long dealerNumber, String deliveryStatusCode, Integer order) throws Exception;
    OrderStatusDto getOrderStatus(String code);
    GenerateOrderResponseDto generateOrder(OrderRequestDto request);
    BasicResponseDto updateOrderStatus(Integer orderNumberCM, String orderStatus);

}
