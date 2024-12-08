package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, Integer> {
    List<WarehouseInventory> findByProduct_ProductId(Integer productId);
    List<WarehouseInventory> findByWarehouseStatus(String status);
    List<WarehouseInventory> findByAvailableQuantityLessThan(Integer quantity);
    List<WarehouseInventory> findByLocation(String location);
}