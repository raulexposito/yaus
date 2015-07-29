package com.raulexposito.yaus.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultUrlCounterStore implements UrlCounterStore {

    private Map<String, List<Visit>> store = new HashMap<>();

    @Override
    public Integer getAmountOfVisitsForShortUrl(final String url) {
        return getVisitsForUrl(url).size();
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
    public Integer getAmountOfDistinctUrlsStored() {
        return store.keySet().size();
    }

    @Override
    public void addVisitForShortUrl(final String url, final String ip, final String userAgent) {
        final List<Visit> visits = getVisitsForUrl(url);
        visits.add(new Visit(ip, userAgent));
        store.put(url, visits);
    }

    protected List<Visit> getVisitsForUrl (final String url) {
        final List<Visit> visits = store.get(url);
        if (visits == null)
            return new ArrayList<>();
        return visits;
    }
}
