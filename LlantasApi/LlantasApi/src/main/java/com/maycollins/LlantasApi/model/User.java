package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    private String userId;

    @Column(name = "nombreusuario")
    private String username;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "rolusuario")
    private String userRole;

    @Column(name = "estadousuario")
    private Boolean userStatus;

    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

    // Constructor vacío
    public User() {
    }

    // Constructor con parámetros
    public User(String userId, String username, String password,
                String userRole, Boolean userStatus, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userStatus=" + userStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}