package com.raulexposito.yaus.persistence.dao;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

    @Override
    public List<Visit> getVisitsForShortUrl(final String shortUrl) {
        final List<Visit> visits = store.get(shortUrl);
        if (visits == null)
            return new ArrayList<>();
        return visits;
    }

    @Override
    public Map<String, Integer> getAmountOfVisitsPerShortUrl() {
        final Map<String, Integer> returnValue = new HashMap<>();
        for (String key: store.keySet()) {
            returnValue.put(key, getAmountOfVisitsForShortUrl(key));
        }
        return returnValue;
    }
}
