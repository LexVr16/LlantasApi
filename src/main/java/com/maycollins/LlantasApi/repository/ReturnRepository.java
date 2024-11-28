package com.maycollins.LlantasApi.repository;
import com.maycollins.LlantasApi.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
    List<Return> findByWarehouse_WarehouseId(Integer warehouseId);
    List<Return> findByProduct_ProductId(Integer productId);
    List<Return> findByReturnStatus(String status);
    List<Return> findByReturnDateBetween(Date startDate, Date endDate);
    List<Return> findByQualityCheck(Boolean qualityCheck);
}