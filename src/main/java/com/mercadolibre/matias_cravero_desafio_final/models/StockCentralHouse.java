package com.mercadolibre.matias_cravero_desafio_final.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock_central_house")
public class StockCentralHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private com.mercadolibre.matias_cravero_desafio_final.models.Part part;
    @ManyToOne
    @JoinColumn(name = "central_house_id", nullable = false)
    private com.mercadolibre.matias_cravero_desafio_final.models.CentralHouse centralHouse;
    private Integer quantity;
}
