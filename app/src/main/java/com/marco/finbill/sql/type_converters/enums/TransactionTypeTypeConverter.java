package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.TransactionType;

public class TransactionTypeTypeConverter {
    @TypeConverter
    public static Integer fromTransactionType(TransactionType transactionType) {
        if (transactionType == null) {
            return null;
        }
        return transactionType.ordinal();
    }

    @TypeConverter
    public static TransactionType toTransactionType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return TransactionType.values()[ordinal];
    }
}
