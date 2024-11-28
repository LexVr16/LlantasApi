package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUser_UserId(Integer userId);
    List<Notification> findByStatus(String status);
    List<Notification> findByPriority(String priority);
    List<Notification> findByNotificationType(String type);
    List<Notification> findBySendDateBetween(Date startDate, Date endDate);
    List<Notification> findByWarehouse_WarehouseId(Integer warehouseId);
}