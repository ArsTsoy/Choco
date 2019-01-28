package com.example.arslan.chocolife.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {


    public static String getDate(String fullDate,String orginalFormat, String resultFormat, Locale locale) throws ParseException {
        String dateAsString = "2019-01-16 22:02:18";
        SimpleDateFormat format = new SimpleDateFormat(orginalFormat);
        Date date = format.parse(dateAsString);
        SimpleDateFormat format2 = new SimpleDateFormat(resultFormat, locale);

        return format2.format(date);
    }

}
