package com.marco.finbill.sql.category;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class CategoryCurrency {

    private int categoryBalanceCurrencyId;
    @Embedded
    private Currency currency;

    public CategoryCurrency(int categoryBalanceCurrencyId, Currency currency) {
        this.categoryBalanceCurrencyId = categoryBalanceCurrencyId;
        this.currency = currency;
    }

    public int getCategoryBalanceCurrencyId() {
        return categoryBalanceCurrencyId;
    }

    public void setCategoryBalanceCurrencyId(int categoryBalanceCurrencyId) {
        this.categoryBalanceCurrencyId = categoryBalanceCurrencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
