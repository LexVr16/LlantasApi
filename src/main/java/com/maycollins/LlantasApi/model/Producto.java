package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    private String productId;

    @Column(name = "serieProducto")
    private String serieProducto;

    @Column(name = "nombreProducto")
    private String nombreProducto;

    @Column(name = "categoriaId")
    private String categoriaId;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio")
    private double precio;

    @Column(name = "productoDefectuso")
    private boolean productoDefectuso;

    // Constructor vacío
    public Producto() {
    }

    public Producto(String productId, String serieProducto, String nombreProducto, String categoriaId, int stock, double precio, boolean productoDefectuso) {
        this.productId = productId;
        this.serieProducto = serieProducto;
        this.nombreProducto = nombreProducto;
        this.categoriaId = categoriaId;
        this.stock = stock;
        this.precio = precio;
        this.productoDefectuso = productoDefectuso;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSerieProducto() {
        return serieProducto;
    }

    public void setSerieProducto(String serieProducto) {
        this.serieProducto = serieProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isProductoDefectuso() {
        return productoDefectuso;
    }

    public void setProductoDefectuso(boolean productoDefectuso) {
        this.productoDefectuso = productoDefectuso;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productId='" + productId + '\'' +
                ", serieProducto='" + serieProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", categoriaId='" + categoriaId + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", productoDefectuso=" + productoDefectuso +
                '}';
    }
}