package com.raulexposito.yaus.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultUrlCounterStore implements UrlCounterStore {

    private final Map<String, List<Visit>> store = new HashMap<>();

    @Override
    public Integer getAmountOfVisitsForShortUrl(final String shortUrl) {
        return getVisitsForShortUrl(shortUrl).size();
    }

    @Override
    public Integer getTotalAmountOfVisits() {
        Integer returnValue = 0;
        for (String key: store.keySet()) {
            returnValue += getAmountOfVisitsForShortUrl(key);
        }
        return returnValue;
    }

    @Override
    public Integer getAmountOfDistinctShortUrlsStored() {
        return store.keySet().size();
    }

    @Override
    public void addVisitForShortUrl(final String shortUrl, final String ip, final String userAgent) {
        final List<Visit> visits = getVisitsForShortUrl(shortUrl);
        visits.add(new Visit(ip, userAgent));
        store.put(shortUrl, visits);
    }

    protected List<Visit> getVisitsForShortUrl(final String shortUrl) {
        final List<Visit> visits = store.get(shortUrl);
        if (visits == null)
            return new ArrayList<>();
        return visits;
    }
}
