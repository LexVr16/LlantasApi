package com.maycollins.LlantasApi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @PostMapping
    public ResponseEntity<Void> actualizarInvetario(@RequestParam Integer cantidad) {
        return ResponseEntity.ok().build();
    }
}
