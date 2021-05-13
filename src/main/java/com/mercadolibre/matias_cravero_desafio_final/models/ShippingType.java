package com.mercadolibre.matias_cravero_desafio_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "shipping_types")
public class ShippingType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "shippingType")
    private List<com.mercadolibre.matias_cravero_desafio_final.models.Order> orders;
}
