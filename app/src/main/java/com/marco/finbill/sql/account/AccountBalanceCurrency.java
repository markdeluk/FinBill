package com.marco.finbill.sql.account;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class AccountBalanceCurrency {
    private int accountBalanceCurrencyId;
    @Embedded
    private Currency currency;

    public AccountBalanceCurrency(int accountBalanceCurrencyId, Currency currency) {
        this.accountBalanceCurrencyId = accountBalanceCurrencyId;
        this.currency = currency;
    }

    public int getAccountBalanceCurrencyId() {
        return accountBalanceCurrencyId;
    }

    public void setAccountBalanceCurrencyId(int accountBalanceCurrencyId) {
        this.accountBalanceCurrencyId = accountBalanceCurrencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
