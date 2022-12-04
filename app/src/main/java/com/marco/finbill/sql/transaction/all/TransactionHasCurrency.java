package com.marco.finbill.sql.transaction.all;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.currency.Currency;

import java.util.List;

public class TransactionHasCurrency {

    @Embedded
    private Currency currency;
    @Relation(
            parentColumn = "currencyId",
            entityColumn = "transactionCurrencyId"
    )
    private List<Transaction> transactions;

    public TransactionHasCurrency(List<Transaction> transactions, Currency currency) {
        this.transactions = transactions;
        this.currency = currency;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
