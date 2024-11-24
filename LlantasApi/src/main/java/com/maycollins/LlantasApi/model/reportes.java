package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
public class reportes {
  
    
    @Id
    private String reporteId;
    

    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @Column(name = "fecha_generado")
    private LocalDateTime fechaGenerado;

    @Column(name = "usuario_id")
    private String usuarioId;

    @Column(name = "contenido")
    private String contenido;

    // Constructor vacío
    public reportes() {
    }

    // Constructor con parámetros
    public reportes(String reporteId, String tipoReporte, LocalDateTime fechaGenerado,
                    String usuarioId, String contenido) {
        this.reporteId = reporteId;
        this.tipoReporte = tipoReporte;
        this.fechaGenerado = fechaGenerado;
        this.usuarioId = usuarioId;
        this.contenido = contenido;
    }

    // Getters y Setters
    public String getReporteId() {
        return reporteId;
    }

    public void setReporteId(String reporteId) {
        this.reporteId = reporteId;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public LocalDateTime getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(LocalDateTime fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "reportes{" +
                "reporteId='" + reporteId + '\'' +
                ", tipoReporte='" + tipoReporte + '\'' +
                ", fechaGenerado=" + fechaGenerado +
                ", usuarioId='" + usuarioId + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }



}
