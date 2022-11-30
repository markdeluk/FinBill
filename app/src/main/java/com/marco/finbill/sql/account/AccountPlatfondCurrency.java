package com.marco.finbill.sql.account;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class AccountPlatfondCurrency {

    private int accountPlatfondCurrencyId;
    @Embedded
    private Currency currency;

    public AccountPlatfondCurrency(int accountPlatfondCurrencyId, Currency currency) {
        this.accountPlatfondCurrencyId = accountPlatfondCurrencyId;
        this.currency = currency;
    }

    public int getAccountPlatfondCurrencyId() {
        return accountPlatfondCurrencyId;
    }

    public void setAccountPlatfondCurrencyId(int accountPlatfondCurrencyId) {
        this.accountPlatfondCurrencyId = accountPlatfondCurrencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
