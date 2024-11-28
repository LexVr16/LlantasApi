package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.WarehouseInventory;
import com.maycollins.LlantasApi.service.WarehouseInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseInventoryController {
    @Autowired
    private WarehouseInventoryService warehouseService;

    @GetMapping
    public List<WarehouseInventory> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseInventory> getWarehouseById(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WarehouseInventory createWarehouse(@RequestBody WarehouseInventory warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseInventory> updateWarehouse(
            @PathVariable Integer id,
            @RequestBody WarehouseInventory warehouse) {
        return warehouseService.getWarehouseById(id)
                .map(existingWarehouse -> {
                    warehouse.setWarehouseId(id);
                    return ResponseEntity.ok(warehouseService.updateWarehouse(warehouse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Integer id) {
        return warehouseService.getWarehouseById(id)
                .map(warehouse -> {
                    warehouseService.deleteWarehouse(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public List<WarehouseInventory> getWarehousesByProduct(@PathVariable Integer productId) {
        return warehouseService.getWarehousesByProduct(productId);
    }

    @GetMapping("/status/{status}")
    public List<WarehouseInventory> getWarehousesByStatus(@PathVariable String status) {
        return warehouseService.getWarehousesByStatus(status);
    }

    @GetMapping("/low-stock")
    public List<WarehouseInventory> getWarehousesWithLowStock(
            @RequestParam(defaultValue = "10") Integer threshold) {
        return warehouseService.getWarehousesWithLowStock(threshold);
    }

    @PostMapping("/{id}/entry")
    public ResponseEntity<Void> registerProductEntry(
            @PathVariable Integer id,
            @RequestParam Integer quantity) {
        warehouseService.registerProductEntry(id, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<Void> registerProductExit(
            @PathVariable Integer id,
            @RequestParam Integer quantity) {
        warehouseService.registerProductExit(id, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check-inventory")
    public ResponseEntity<Void> checkInventoryLevels() {
        warehouseService.checkInventoryLevels();
        return ResponseEntity.ok().build();
    }
}