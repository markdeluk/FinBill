package com.marco.finbill.sql.category;

import android.util.Log;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.currency.Currency;
public class CategoryWithCurrency {

    @Embedded
    private Category category;

    @Relation(
            parentColumn = "categoryBalanceCurrencyId",
            entityColumn = "currencyId"
    )
    private Currency currency;

    public CategoryWithCurrency(Category category, Currency currency) {
        this.category = category;
        this.currency = currency;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean equals(CategoryWithCurrency categoryWithCurrency) {
        return category.equals(categoryWithCurrency.getCategory()) && currency.equals(categoryWithCurrency.getCurrency());
    }

}