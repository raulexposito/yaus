package com.raulexposito.yaus.persistence.dao;

import lombok.Getter;
import org.joda.time.LocalDateTime;

@Getter
public class Visit {

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer hour;

    private Integer minute;

    private Integer second;

    private Integer weekday;

    private String ip;

    private String userAgent;

    public Visit (final String ip, final String userAgent) {
        generate(ip, userAgent, new LocalDateTime());
    }

    // ---------------------------------------------
    // package protected
    // just for testing purposes
    // ---------------------------------------------

    Visit (final String ip, final String userAgent, final LocalDateTime localDateTime) {
        generate(ip, userAgent, localDateTime);
    }

    void generate (final String ip, final String userAgent, final LocalDateTime localDateTime) {
        this.ip = ip;
        this.userAgent = userAgent;
        year = localDateTime.getYear();
        month = localDateTime.getMonthOfYear();
        day = localDateTime.getDayOfMonth();
        hour = localDateTime.getHourOfDay();
        minute = localDateTime.getMinuteOfHour();
        second = localDateTime.getSecondOfMinute();
        weekday = localDateTime.getDayOfWeek();
    }
}
