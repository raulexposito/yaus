package com.raulexposito.yaus.persistence.dao;

import lombok.Data;

@Data
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
        this.ip = ip;
        this.userAgent = userAgent;
    }
}
