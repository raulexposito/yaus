package com.raulexposito.yaus.service;

import com.raulexposito.yaus.persistence.dao.UrlCounterStore;
import com.raulexposito.yaus.persistence.dao.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitingService {

    @Autowired
    private UrlCounterStore urlCounterStore;

    public void addVisit (final String shortUrl, final String ip, final String userAgent) {
        urlCounterStore.addVisitForShortUrl(shortUrl, ip, userAgent);
    }

    public List<Visit> getVisitsForShortUrl (final String shortUrl) {
        return urlCounterStore.getVisitsForShortUrl(shortUrl);
    }
}
