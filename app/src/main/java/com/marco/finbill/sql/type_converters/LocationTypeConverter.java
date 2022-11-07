package com.marco.finbill.sql.type_converters;

import androidx.room.TypeConverter;

public class LocationTypeConverter {
    @TypeConverter
    public static String locationToString(android.location.Location location) {
        return location.getLatitude() + "," + location.getLongitude();
    }

    @TypeConverter
    public static android.location.Location fromString(String value) {
        String[] parts = value.split(",");
        android.location.Location location = new android.location.Location("");
        location.setLatitude(Double.parseDouble(parts[0]));
        location.setLongitude(Double.parseDouble(parts[1]));
        return location;
    }
}
