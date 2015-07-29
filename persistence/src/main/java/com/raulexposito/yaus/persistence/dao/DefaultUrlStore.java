package com.raulexposito.yaus.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultUrlStore implements UrlStore {

    private Map<String, List<Visit>> store = new HashMap<>();

    @Override
    public Integer getAmountOfVisitsForUrl(String url) {
        final List<Visit> visits = store.get(url);
        if (visits == null)
            return 0;
        return visits.size();
    }

    @Override
    public Integer getTotalAmountOfVisits() {
        Integer returnValue = 0;
        for (String key: store.keySet()) {
            returnValue += getAmountOfVisitsForUrl(key);
        }
        return returnValue;
    }

    @Override
    public Integer getAmountOfDistinctUrlsStored() {
        return store.keySet().size();
    }
}
