package com.project.sellit.utils;

import java.sql.Timestamp;

public class DateUtil {
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
