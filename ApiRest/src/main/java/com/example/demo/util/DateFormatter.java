package com.example.demo.util;

import java.sql.Timestamp;

public class DateFormatter {
    public static String getStringDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }


}
