package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "ventas")
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venta_id")
    private Integer ventaId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;

    @Column(name = "precio_total")
    private Double precioTotal;

    @Column(name = "cantidad_vendida")
    private Integer cantidadVendida;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "descuento")
    private Double descuento;

    @Column(name = "total")
    private Double total;

    @Column(name = "metodo_pago")
    private String metodoPago;

    public Double agregarProducto() {
        // Implementar lógica para agregar producto
        return 0.0;
    }

    public Double calcularTotal() {
        // Implementar lógica para calcular total
        return 0.0;
    }

    public void generarComprobante() {
        // Implementar lógica para generar comprobante
    }

    public void cancelarVenta() {
        // Implementar lógica para cancelar venta
    }
}
