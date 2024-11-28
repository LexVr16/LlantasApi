package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @PatchMapping
    public ResponseEntity<Void> actualizarStockPorIdProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok().build();
    }
}
