package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.TransactionRecurrency;

public class TransactionRecurrencyTypeConverter {
    @TypeConverter
    public static Integer fromTransactionRecurrencyType(TransactionRecurrency transactionRecurrency) {
        if (transactionRecurrency == null) {
            return null;
        }
        return transactionRecurrency.ordinal();
    }

    @TypeConverter
    public static TransactionRecurrency toTransactionRecurrencyType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return TransactionRecurrency.values()[ordinal];
    }
}