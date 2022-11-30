package com.marco.finbill.sql.transaction.all;

import com.marco.finbill.sql.currency.Currency;

public class TransactionCurrency {
    private int transactionCurrencyId;
    private Currency currency;

    public TransactionCurrency(int transactionCurrencyId, Currency currency) {
        this.transactionCurrencyId = transactionCurrencyId;
        this.currency = currency;
    }

    public int getTransactionCurrencyId() {
        return transactionCurrencyId;
    }

    public void setTransactionCurrencyId(int transactionCurrencyId) {
        this.transactionCurrencyId = transactionCurrencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
