package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.TransactionFrequency;

public class TransactionFrequencyTypeConverter {
    @TypeConverter
    public static Integer fromTransactionFrequencyType(TransactionFrequency transactionFrequency) {
        if (transactionFrequency == null) {
            return null;
        }
        return transactionFrequency.ordinal();
    }

    @TypeConverter
    public static TransactionFrequency toTransactionFrequencyType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return TransactionFrequency.values()[ordinal];
    }
}