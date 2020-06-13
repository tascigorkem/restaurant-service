package com.tascigorkem.restaurantservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final String GLOBAL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    private static final DateTimeFormatter GlobalDateFormatter = DateTimeFormatter.ofPattern(GLOBAL_DATE_FORMAT);

    private static DateUtil instance;

    public static DateUtil getInstance() {
        if (instance == null) {
            synchronized (DateUtil.class) {
                if (instance == null) {
                    instance = new DateUtil();
                }
            }
        }
        return instance;
    }

    // String - Date Conversions
    public LocalDateTime toLocalDateTime(String strDate) {
        return LocalDateTime.parse(strDate, GlobalDateFormatter);
    }

    public Date toDate(String strDate) throws ParseException {
        return new SimpleDateFormat(GLOBAL_DATE_FORMAT).parse(strDate);
    }

    // Date -> LocalDate
    public LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    // LocalDate -> Date
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}