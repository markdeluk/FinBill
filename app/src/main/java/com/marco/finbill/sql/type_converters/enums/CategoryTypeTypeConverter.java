package com.marco.finbill.sql.type_converters.enums;

import androidx.room.TypeConverter;

import com.marco.finbill.enums.CategoryType;

public class CategoryTypeTypeConverter {
    @TypeConverter
    public static Integer fromCategoryType(CategoryType categoryType) {
        if (categoryType == null) {
            return null;
        }
        return categoryType.ordinal();
    }

    @TypeConverter
    public static CategoryType toCategoryType(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return CategoryType.values()[ordinal];
    }
}
