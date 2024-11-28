package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Notification;
import com.maycollins.LlantasApi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Integer id) {
        return notificationRepository.findById(id);
    }

    public Notification createNotification(Notification notification) {
        notification.setSendDate(new Date());
        notification.setStatus("Unread");
        notification.sendNotification();
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Integer id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.ifPresent(n -> {
            n.deleteNotification();
            notificationRepository.deleteById(id);
        });
    }

    public List<Notification> getNotificationsByUser(Integer userId) {
        return notificationRepository.findByUser_UserId(userId);
    }

    public List<Notification> getNotificationsByStatus(String status) {
        return notificationRepository.findByStatus(status);
    }

    public List<Notification> getNotificationsByPriority(String priority) {
        return notificationRepository.findByPriority(priority);
    }

    public List<Notification> getNotificationsByType(String type) {
        return notificationRepository.findByNotificationType(type);
    }

    public void markAsRead(Integer id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.ifPresent(n -> {
            n.markAsRead();
            notificationRepository.save(n);
        });
    }

    public void archiveNotification(Integer id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        notification.ifPresent(n -> {
            n.archiveNotification();
            notificationRepository.save(n);
        });
    }
}