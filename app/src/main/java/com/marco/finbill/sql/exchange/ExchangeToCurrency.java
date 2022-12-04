package com.marco.finbill.sql.exchange;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class ExchangeToCurrency {
    private int exchangeToCurrencyId;
    @Embedded
    private Currency currency;

    public ExchangeToCurrency(int exchangeToCurrencyId, Currency currency) {
        this.exchangeToCurrencyId = exchangeToCurrencyId;
        this.currency = currency;
    }

    public int getExchangeToCurrencyId() {
        return exchangeToCurrencyId;
    }

    public void setExchangeToCurrencyId(int exchangeToCurrencyId) {
        this.exchangeToCurrencyId = exchangeToCurrencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
