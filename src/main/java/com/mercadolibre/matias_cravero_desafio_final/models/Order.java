package com.mercadolibre.matias_cravero_desafio_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private Integer orderNumberCM;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Integer daysDelayed;
    @ManyToOne
    @JoinColumn(name = "delivery_status_id", nullable = false)
    private com.mercadolibre.matias_cravero_desafio_final.models.DeliveryStatus deliveryStatus;
    @ManyToOne
    @JoinColumn(name = "concessionarie_id", nullable = false)
    private com.mercadolibre.matias_cravero_desafio_final.models.Concessionarie concessionarie;

    @ManyToOne
    @JoinColumn(name = "shipping_type_id", nullable = false)
    private ShippingType shippingType;
    @ManyToOne
    @JoinColumn(name = "central_house_id", nullable = false)
    private CentralHouse centralHouse;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

}
