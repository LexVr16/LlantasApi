package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Return;
import com.maycollins.LlantasApi.repository.ReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnService {
    @Autowired
    private ReturnRepository returnRepository;

    @Autowired
    private WarehouseInventoryService warehouseService;

    public List<Return> getAllReturns() {
        return returnRepository.findAll();
    }

    public Optional<Return> getReturnById(Integer id) {
        return returnRepository.findById(id);
    }

    public Return createReturn(Return returnItem) {
        returnItem.setReturnDate(new Date());
        returnItem.setReturnStatus("Pending");
        returnItem.setQualityCheck(false);
        return returnRepository.save(returnItem);
    }

    public Return updateReturn(Return returnItem) {
        returnItem.setLastUpdateDate(new Date());
        return returnRepository.save(returnItem);
    }

    public void deleteReturn(Integer id) {
        returnRepository.deleteById(id);
    }

    public List<Return> getReturnsByWarehouse(Integer warehouseId) {
        return returnRepository.findByWarehouse_WarehouseId(warehouseId);
    }

    public List<Return> getReturnsByProduct(Integer productId) {
        return returnRepository.findByProduct_ProductId(productId);
    }

    public List<Return> getReturnsByStatus(String status) {
        return returnRepository.findByReturnStatus(status);
    }

    public List<Return> getReturnsByDateRange(Date startDate, Date endDate) {
        return returnRepository.findByReturnDateBetween(startDate, endDate);
    }

    public Return processReturn(Integer id) {
        Optional<Return> returnOptional = returnRepository.findById(id);
        if (returnOptional.isPresent()) {
            Return returnItem = returnOptional.get();
            if (returnItem.verifyCondition()) {
                returnItem.setQualityCheck(true);
                if (returnItem.approveReturn()) {
                    returnItem.setReturnStatus("Approved");
                    returnItem.processStockChange();
                    returnItem.finalizeReturn();
                }
            } else {
                returnItem.setReturnStatus("Rejected");
            }
            return returnRepository.save(returnItem);
        }
        return null;
    }

    public Return approveReturn(Integer id) {
        Optional<Return> returnOptional = returnRepository.findById(id);
        if (returnOptional.isPresent()) {
            Return returnItem = returnOptional.get();
            returnItem.setReturnStatus("Approved");
            returnItem.setLastUpdateDate(new Date());
            return returnRepository.save(returnItem);
        }
        return null;
    }

    public Return rejectReturn(Integer id) {
        Optional<Return> returnOptional = returnRepository.findById(id);
        if (returnOptional.isPresent()) {
            Return returnItem = returnOptional.get();
            returnItem.setReturnStatus("Rejected");
            returnItem.setLastUpdateDate(new Date());
            return returnRepository.save(returnItem);
        }
        return null;
    }
}