package com.example.demo.util;

import com.example.demo.util.DateFormatter;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateFormatterTest {

    @Test
    void testGetStringDate() {
        String dateString = DateFormatter.getStringDate();

        assertEquals(true, dateString.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3}"));
    }

    @Test
    void testGetTimeStamp() {
        Long timestamp = DateFormatter.getTimeStamp();

        assertEquals(true, timestamp > 0);
    }
}