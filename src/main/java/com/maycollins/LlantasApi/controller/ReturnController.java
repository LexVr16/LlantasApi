package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.Return;
import com.maycollins.LlantasApi.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @GetMapping
    public List<Return> getAllReturns() {
        return returnService.getAllReturns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Return> getReturnById(@PathVariable Integer id) {
        return returnService.getReturnById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Return createReturn(@RequestBody Return returnItem) {
        return returnService.createReturn(returnItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Return> updateReturn(
            @PathVariable Integer id,
            @RequestBody Return returnItem) {
        return returnService.getReturnById(id)
                .map(existingReturn -> {
                    returnItem.setReturnId(id);
                    return ResponseEntity.ok(returnService.updateReturn(returnItem));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturn(@PathVariable Integer id) {
        return returnService.getReturnById(id)
                .map(returnItem -> {
                    returnService.deleteReturn(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<Return> getReturnsByWarehouse(@PathVariable Integer warehouseId) {
        return returnService.getReturnsByWarehouse(warehouseId);
    }

    @GetMapping("/product/{productId}")
    public List<Return> getReturnsByProduct(@PathVariable Integer productId) {
        return returnService.getReturnsByProduct(productId);
    }

    @GetMapping("/status/{status}")
    public List<Return> getReturnsByStatus(@PathVariable String status) {
        return returnService.getReturnsByStatus(status);
    }

    @GetMapping("/date-range")
    public List<Return> getReturnsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return returnService.getReturnsByDateRange(startDate, endDate);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<Return> processReturn(@PathVariable Integer id) {
        Return processedReturn = returnService.processReturn(id);
        return processedReturn != null ?
                ResponseEntity.ok(processedReturn) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Return> approveReturn(@PathVariable Integer id) {
        Return approvedReturn = returnService.approveReturn(id);
        return approvedReturn != null ?
                ResponseEntity.ok(approvedReturn) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Return> rejectReturn(@PathVariable Integer id) {
        Return rejectedReturn = returnService.rejectReturn(id);
        return rejectedReturn != null ?
                ResponseEntity.ok(rejectedReturn) :
                ResponseEntity.notFound().build();
    }
}