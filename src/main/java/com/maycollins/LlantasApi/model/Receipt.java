package com.maycollins.LlantasApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "receipts")
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Integer receiptId;

    @Column(name = "receipt_number")
    private String receiptNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "issue_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    private String details;

    @Column(name = "receipt_type")
    private String receiptType; // Invoice, Ticket

    @Column(name = "receipt_status")
    private String receiptStatus; // Issued, Cancelled, Void

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "receipt_state")
    private String receiptState; // Emitted, Cancelled

    public void generateReceipt() {
        // Implement receipt generation logic
    }

    public void cancelReceipt() {
        this.receiptStatus = "Cancelled";
    }

    public void voidReceipt() {
        this.receiptStatus = "Void";
    }

    public void registerPayment() {
        // Implement payment registration logic
    }
}