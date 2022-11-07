package com.marco.finbill.sql.type_converters;

import androidx.room.TypeConverter;

import java.sql.Time;

public class TimeTypeConverter {
    @TypeConverter
    public static Time fromTimestamp(Long value) {
        return value == null ? null : new Time(value);
    }

    @TypeConverter
    public static Long timeToTimestamp(Time time) {
        return time == null ? null : time.getTime();
    }
}
