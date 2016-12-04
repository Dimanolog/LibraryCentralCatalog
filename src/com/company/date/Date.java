package com.company.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Dimanolog on 26.12.2015.
 */
public class Date
{
    public static String getCurrentDate()
    {
        return new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
    }
}
