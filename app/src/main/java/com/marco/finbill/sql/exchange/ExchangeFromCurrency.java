package com.marco.finbill.sql.exchange;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class ExchangeFromCurrency {
    private int exchangeFromCurrencyId;
    @Embedded
    private Currency currency;

    public ExchangeFromCurrency(int exchangeFromCurrencyId, Currency currency) {
        this.exchangeFromCurrencyId = exchangeFromCurrencyId;
        this.currency = currency;
    }

    public int getExchangeFromCurrencyId() {
        return exchangeFromCurrencyId;
    }

    public void setExchangeFromCurrencyId(int exchangeFromCurrencyId) {
        this.exchangeFromCurrencyId = exchangeFromCurrencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
