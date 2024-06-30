package com.hcl.bankingservice.model;

import com.hcl.bankingservice.enums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "transaction_amount", nullable = false)
    private double transactionAmount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne()
    @JoinColumn(name = "credit_card_id", referencedColumnName = "credit_card_id")
    private CreditCard creditCard;

    @ManyToOne()
    @JoinColumn(name = "debit_card_id", referencedColumnName = "debit_card_id")
    private DebitCard debitCard;

    public Transaction() {
    }

    public Transaction(long transactionId, LocalDate transactionDate, double transactionAmount, TransactionType transactionType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }

    public Transaction(long transactionId, LocalDate transactionDate, double transactionAmount, TransactionType transactionType, CreditCard creditCard, DebitCard debitCard) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.creditCard = creditCard;
        this.debitCard = debitCard;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }
}
