package com.cesac.cece01;


import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {
    @TypeConverter
    public long convertDateToLong(Date date) {
        return date.getTime();
    }
    @TypeConverter
    public Date convertLongToDate(long time) {
        return new Date(time);
    }
}