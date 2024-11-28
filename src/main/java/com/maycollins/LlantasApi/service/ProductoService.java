package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Producto;
import com.maycollins.LlantasApi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    void actualizarStockPorIdProducto(Producto producto) {
        productoRepository.save(producto);
    }
}
