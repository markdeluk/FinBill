package com.marco.finbill.sql.transaction.all;

import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.currency.Currency;

import java.util.List;
public class TransactionHasCurrency {

    @Embedded
    private Transaction transaction;

    @Relation(
            parentColumn = "transactionCurrencyId",
            entityColumn = "currencyId"
    )
    private Currency transactionCurrency;

    public TransactionHasCurrency(Transaction transaction, Currency transactionCurrency) {
        this.transaction = transaction;
        this.transactionCurrency = transactionCurrency;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Currency getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(Currency transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }
}