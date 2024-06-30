package com.hcl.bankingservice.dto;

import com.hcl.bankingservice.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;

public class TransactionDto {

    private long transactionId;

    private LocalDate transactionDate;

    @NotNull
    private double transactionAmount;

    @NotNull
    private TransactionType tractionType;

    private long creditCardId;

    private long debitCardId;

    public TransactionDto() {
    }

    public TransactionDto(long transactionId, LocalDate transactionDate, double transactionAmount, TransactionType tractionType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.tractionType = tractionType;
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

    public TransactionType getTractionType() {
        return tractionType;
    }

    public void setTractionType(TransactionType tractionType) {
        this.tractionType = tractionType;
    }

    public long getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(long creditCardId) {
        this.creditCardId = creditCardId;
    }

    public long getDebitCardId() {
        return debitCardId;
    }

    public void setDebitCardId(long debitCardId) {
        this.debitCardId = debitCardId;
    }
}
