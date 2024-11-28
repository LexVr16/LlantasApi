package com.maycollins.LlantasApi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "product_categories")
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    private String brand;
    private String model;
    private String measure;

    @Column(name = "tool_type")
    private String toolType;

    @Column(name = "spare_part_type")
    private String sparePartType;

    private String specifications;
    private String compatibility;

    public boolean checkAvailability() {
        // Implement verification logic
        return true;
    }
}