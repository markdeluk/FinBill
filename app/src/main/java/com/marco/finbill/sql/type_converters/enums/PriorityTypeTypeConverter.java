package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.PriorityType;

public class PriorityTypeTypeConverter {
    @TypeConverter
    public static Integer fromPriorityType(PriorityType priorityType) {
        if (priorityType == null) {
            return null;
        }
        return priorityType.ordinal();
    }

    @TypeConverter
    public static PriorityType toPriorityType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return PriorityType.values()[ordinal];
    }
}
