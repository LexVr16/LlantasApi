package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.WarehouseInventory;
import com.maycollins.LlantasApi.repository.WarehouseInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseInventoryService {
    @Autowired
    private WarehouseInventoryRepository warehouseInventoryRepository;

    public List<WarehouseInventory> getAllWarehouses() {
        return warehouseInventoryRepository.findAll();
    }

    public Optional<WarehouseInventory> getWarehouseById(Integer id) {
        return warehouseInventoryRepository.findById(id);
    }

    public WarehouseInventory createWarehouse(WarehouseInventory warehouse) {
        return warehouseInventoryRepository.save(warehouse);
    }

    public WarehouseInventory updateWarehouse(WarehouseInventory warehouse) {
        return warehouseInventoryRepository.save(warehouse);
    }

    public void deleteWarehouse(Integer id) {
        warehouseInventoryRepository.deleteById(id);
    }

    public List<WarehouseInventory> getWarehousesByProduct(Integer productId) {
        return warehouseInventoryRepository.findByProduct_ProductId(productId);
    }

    public List<WarehouseInventory> getWarehousesByStatus(String status) {
        return warehouseInventoryRepository.findByWarehouseStatus(status);
    }

    public List<WarehouseInventory> getWarehousesWithLowStock(Integer threshold) {
        return warehouseInventoryRepository.findByAvailableQuantityLessThan(threshold);
    }

    public void registerProductEntry(Integer warehouseId, Integer quantity) {
        Optional<WarehouseInventory> warehouse = warehouseInventoryRepository.findById(warehouseId);
        warehouse.ifPresent(w -> {
            w.setAvailableQuantity(w.getAvailableQuantity() + quantity);
            w.registerEntry();
            warehouseInventoryRepository.save(w);
        });
    }

    public void registerProductExit(Integer warehouseId, Integer quantity) {
        Optional<WarehouseInventory> warehouse = warehouseInventoryRepository.findById(warehouseId);
        warehouse.ifPresent(w -> {
            if (w.getAvailableQuantity() >= quantity) {
                w.setAvailableQuantity(w.getAvailableQuantity() - quantity);
                w.registerExit();
                warehouseInventoryRepository.save(w);
            }
        });
    }

    public void checkInventoryLevels() {
        List<WarehouseInventory> allWarehouses = warehouseInventoryRepository.findAll();
        for (WarehouseInventory warehouse : allWarehouses) {
            warehouse.checkLowStockAlert();
            warehouseInventoryRepository.save(warehouse);
        }
    }
}