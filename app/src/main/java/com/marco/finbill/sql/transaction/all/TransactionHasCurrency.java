package com.marco.finbill.sql.transaction.all;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class TransactionHasCurrency {

    @Embedded
    private Transaction transaction;
    @Embedded
    private Currency currency;

    public TransactionHasCurrency(Transaction transaction, Currency currency) {
        this.transaction = transaction;
        this.currency = currency;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
