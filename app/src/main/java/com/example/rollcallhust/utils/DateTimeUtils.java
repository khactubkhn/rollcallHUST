package com.example.rollcallhust.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DateTimeUtils {
    public static String formatDate(String dateStr){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf2.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf1.format(date);
    }
}
