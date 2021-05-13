package com.mercadolibre.matias_cravero_desafio_final.util;

import com.mercadolibre.matias_cravero_desafio_final.dto.responses.OrderStatusDto;
import com.mercadolibre.matias_cravero_desafio_final.models.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderStatusMapper {

    public OrderStatusDto mapList(Order order) {
        return map(order);
    }

    public OrderStatusDto map(Order order){
        OrderStatusDto orderDto = new OrderStatusDto();
        orderDto.setOrderNumberCE(
                intToCodeString(4,
                        String.valueOf(order.getConcessionarie().getId())) + "-"
                        + intToCodeString(8, String.valueOf(order.getOrderNumberCM())));
        orderDto.setOrderDate(order.getOrderDate().toString());
        orderDto.setOrderStatus(order.getDeliveryStatus().getCode());
        com.mercadolibre.matias_cravero_desafio_final.util.OrderMapper orderMapper = new com.mercadolibre.matias_cravero_desafio_final.util.OrderMapper();
        orderDto.setOrderDetails(orderMapper.mapOrderDetails(order.getOrderDetails()));
        return orderDto;
    }

    public String intToCodeString(int len, String aux) {
        String res = "";
        for (int j = 0; j < len - aux.length(); j++)
            res = res + "0";
        return res + aux;
    }
}
