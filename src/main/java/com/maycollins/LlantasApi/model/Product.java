package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_serial")
    private String productSerial;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    private Double price;

    @Column(name = "is_defective")
    private Boolean isDefective;

    @Column(name = "warranty_time")
    @Temporal(TemporalType.DATE)
    private Date warrantyTime;

    public void updateProductStock(Integer productId, Integer quantity) {
        // Implement stock update logic
    }

    public boolean checkAvailability() {
        // Implement availability check logic
        return true;
    }

    public void updatePrice() {
        // Implement price update logic
    }
}