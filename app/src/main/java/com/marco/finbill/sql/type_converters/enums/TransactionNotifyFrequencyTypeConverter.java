package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.TransactionNotifyFrequency;

public class TransactionNotifyFrequencyTypeConverter {
    @TypeConverter
    public static Integer fromTransactionNotifyFrequencyType(TransactionNotifyFrequency transactionNotifyFrequency) {
        if (transactionNotifyFrequency == null) {
            return null;
        }
        return transactionNotifyFrequency.ordinal();
    }

    @TypeConverter
    public static TransactionNotifyFrequency toTransactionNotifyFrequencyType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return TransactionNotifyFrequency.values()[ordinal];
    }
}
