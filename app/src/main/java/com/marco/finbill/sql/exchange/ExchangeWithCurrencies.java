package com.marco.finbill.sql.exchange;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.currency.Currency;
public class ExchangeWithCurrencies {
    @Embedded
    private Exchange exchange;

    @Relation(
            parentColumn = "exchangeFromCurrencyId",
            entityColumn = "currencyId"
    )
    private Currency exchangeFromCurrency;

    @Relation(
            parentColumn = "exchangeToCurrencyId",
            entityColumn = "currencyId"
    )
    private Currency exchangeToCurrency;

    public ExchangeWithCurrencies(Exchange exchange, Currency exchangeFromCurrency, Currency exchangeToCurrency) {
        this.exchange = exchange;
        this.exchangeFromCurrency = exchangeFromCurrency;
        this.exchangeToCurrency = exchangeToCurrency;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
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