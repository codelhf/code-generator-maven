package com.example.generator.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class DateTimeUtil {

    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String dateToStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
}
