package com.anu.gp24s1.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used for convert between different types.
 * @author qinjuewu
 */
public class TypeConvert {

    /**
     * Converts a string representing a date and time in the format "yyyy-MM-dd HH:mm:ss" to a Date object.
     * @param dateString String
     * @return date Date
     * @author Qinjue Wu
     */
    public static Date strToDate(String dateString)
    {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
