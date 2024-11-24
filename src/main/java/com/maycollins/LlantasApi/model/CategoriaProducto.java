package com.maycollins.LlantasApi.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CategoriaProducto")
@Getter @Setter
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_categoria", nullable = false)
    private String tipoCategoria;

    @Column(name = "marca",nullable = false)
    private String marca;

    @Column(name = "modelo",nullable = false)
    private String modelo;

    @Column(name = "medida",nullable = false)
    private String medida;

    @Column(name = "tipo_herramienta", nullable = true)
    private String tipoHerramienta;

    @Column(name = "especificaciones",nullable = true)
    private String especificaciones;

    @Column(name = "tipo_refaccion", nullable = true)
    private String tipoRefaccion;

    @Column(name = "compatibilidad", nullable = true)
    private String compatibilidad;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    public CategoriaProducto() {
    }

    public CategoriaProducto(Integer id, String tipoCategoria, String marca, String modelo,
                             String medida, String tipoHerramienta, String especificaciones,
                             String tipoRefaccion, String compatibilidad, List<Producto> productos) {
        this.id = id;
        this.tipoCategoria = tipoCategoria;
        this.marca = marca;
        this.modelo = modelo;
        this.medida = medida;
        this.tipoHerramienta = tipoHerramienta;
        this.especificaciones = especificaciones;
        this.tipoRefaccion = tipoRefaccion;
        this.compatibilidad = compatibilidad;
        this.productos = productos;
    }
}
