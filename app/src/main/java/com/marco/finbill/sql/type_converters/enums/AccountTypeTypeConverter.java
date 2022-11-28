package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.AccountType;

public class AccountTypeTypeConverter {
    @TypeConverter
    public static Integer fromAccountType(AccountType accountType) {
        if (accountType == null) {
            return null;
        }
        return accountType.ordinal();
    }

    @TypeConverter
    public static AccountType toAccountType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return AccountType.values()[ordinal];
    }
}
