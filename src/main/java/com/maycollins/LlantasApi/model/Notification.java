package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;

    @Column(name = "notification_type")
    private String notificationType; // Alert, Information, Warning, Error

    private String title;

    @Column(name = "notification_message")
    private String notificationMessage;

    private String priority; // High, Medium, Low

    private String status; // Unread, Read, Archived

    @Column(name = "send_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

    @Column(name = "read_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readDate;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseInventory warehouse;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void sendNotification() {
        // Implement notification sending logic
    }

    public void markAsRead() {
        this.status = "Read";
        this.readDate = new Date();
    }

    public void archiveNotification() {
        this.status = "Archived";
    }

    public void deleteNotification() {
        // Implement deletion logic
    }
}