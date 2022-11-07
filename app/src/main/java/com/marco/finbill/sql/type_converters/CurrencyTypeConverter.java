package com.marco.finbill.sql.type_converters;

import android.icu.util.Currency;

import androidx.room.TypeConverter;

public class CurrencyTypeConverter {
    @TypeConverter
    public static Currency fromString(String value) {
        return Currency.getInstance(value);
    }

    @TypeConverter
    public static String currencyToString(Currency currency) {
        return currency.getCurrencyCode();
    }
}
