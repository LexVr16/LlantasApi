package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.Notification;
import com.maycollins.LlantasApi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Integer id) {
        return notificationService.getNotificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable Integer id,
            @RequestBody Notification notification) {
        return notificationService.getNotificationById(id)
                .map(existingNotification -> {
                    notification.setNotificationId(id);
                    return ResponseEntity.ok(notificationService.updateNotification(notification));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Integer id) {
        return notificationService.getNotificationById(id)
                .map(notification -> {
                    notificationService.deleteNotification(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationsByUser(@PathVariable Integer userId) {
        return notificationService.getNotificationsByUser(userId);
    }

    @GetMapping("/status/{status}")
    public List<Notification> getNotificationsByStatus(@PathVariable String status) {
        return notificationService.getNotificationsByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Notification> getNotificationsByPriority(@PathVariable String priority) {
        return notificationService.getNotificationsByPriority(priority);
    }

    @GetMapping("/type/{type}")
    public List<Notification> getNotificationsByType(@PathVariable String type) {
        return notificationService.getNotificationsByType(type);
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archiveNotification(@PathVariable Integer id) {
        notificationService.archiveNotification(id);
        return ResponseEntity.ok().build();
    }
}