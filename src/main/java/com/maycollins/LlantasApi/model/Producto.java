package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Producto")
@Getter @Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serie_producto", nullable = false, unique = true)
    private String serieProducto;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private String categoriaId;

    @Column(name = "stock",nullable = false)
    private Integer stock;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "producto_defectuoso", nullable = false)
    private Boolean productoDefectuso;

    // Constructor vacío
    public Producto() {
    }

    public Producto(Integer id, String serieProducto, String categoriaId, Integer stock,
                    Double precio, Boolean productoDefectuso) {
        this.id = id;
        this.serieProducto = serieProducto;
        this.categoriaId = categoriaId;
        this.stock = stock;
        this.precio = precio;
        this.productoDefectuso = productoDefectuso;
    }
}