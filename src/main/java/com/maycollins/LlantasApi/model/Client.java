package com.maycollins.LlantasApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "history_notes")
    private String historyNotes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date")
    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_category")
    private ClientCategory category;

    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "payment_method_preference")
    private String paymentMethodPreference;
}
