package com.mercadolibre.matias_cravero_desafio_final.util;

import com.mercadolibre.matias_cravero_desafio_final.dto.OrderDetailDto;
import com.mercadolibre.matias_cravero_desafio_final.models.*;
import com.mercadolibre.matias_cravero_desafio_final.repositories.PartRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {
    private static com.mercadolibre.matias_cravero_desafio_final.util.OrderMapper mapper = new com.mercadolibre.matias_cravero_desafio_final.util.OrderMapper();
    private PartRepository repoParts;
    public OrderUtil(PartRepository repoP){
        this.repoParts = repoP;
    }

    public Order generateOrder(Concessionarie conc, ShippingType shippingType, CentralHouse centralHouse,
                                      DeliveryStatus deliveryStatus, Integer orderNumberCM){
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCentralHouse(centralHouse);
        order.setConcessionarie(conc);
        order.setShippingType(shippingType);
        order.setDeliveryStatus(deliveryStatus);
        order.setOrderNumberCM(orderNumberCM);
        order.setDaysDelayed(0);
        order.setDeliveryDate(LocalDate.now().plusDays(7));
        return order;
    }

    public List<OrderDetail> reverseMapOrderDetailDto(List<OrderDetailDto> orderDetailsDtos, Order newOrder){
        List<OrderDetail> response = new ArrayList<>();
        for(OrderDetailDto od : orderDetailsDtos){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(newOrder);
            orderDetail.setDescription(od.getDescription());
            orderDetail.setQuantity(od.getQuantity());
            orderDetail.setReason(od.getReason());
            Part part = repoParts.findPartByPartCode(od.getPartCode()).orElse(null);
            orderDetail.setPart(part);
            response.add(orderDetail);
        }
        return response;
    }
}
