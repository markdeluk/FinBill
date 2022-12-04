package com.marco.finbill.sql.exchange;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class ExchangeWithCurrencies {
    @Embedded
    private Currency exchangeFromCurrency;
    @Embedded
    private Currency exchangeToCurrency;

    public ExchangeWithCurrencies(Currency exchangeFromCurrency, Currency exchangeToCurrency) {
        this.exchangeFromCurrency = exchangeFromCurrency;
        this.exchangeToCurrency = exchangeToCurrency;
    }

    public Currency getExchangeFromCurrency() {
        return exchangeFromCurrency;
    }

    public void setExchangeFromCurrency(Currency exchangeFromCurrency) {
        this.exchangeFromCurrency = exchangeFromCurrency;
    }

    public Currency getExchangeToCurrency() {
        return exchangeToCurrency;
    }

    public void setExchangeToCurrency(Currency exchangeToCurrency) {
        this.exchangeToCurrency = exchangeToCurrency;
    }
}
