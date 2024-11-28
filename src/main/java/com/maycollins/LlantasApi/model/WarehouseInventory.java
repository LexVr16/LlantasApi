package com.maycollins.LlantasApi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "warehouse_inventory")
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    private String location;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @Column(name = "maximum_capacity")
    private Integer maximumCapacity;

    @Column(name = "warehouse_status")
    private String warehouseStatus; // Active, Maintenance

    public void manageInventory() {
        // Implement inventory management logic
    }

    public void registerEntry() {
        // Implement entry registration logic
    }

    public void registerExit() {
        // Implement exit registration logic
    }

    public void registerProduct() {
        // Implement product registration logic
    }

    public void checkLowStockAlert() {
        // Implement low stock alert logic
    }
}