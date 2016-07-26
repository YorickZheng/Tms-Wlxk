package com.wlxk.support.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by malin on 2016/7/22.
 */
public class DateUtil {
    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date) {
        return dateToString(date, DEFAULT_FORMAT);
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
