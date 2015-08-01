package com.raulexposito.yaus.persistence.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DefaultUrlCounterStore implements UrlCounterStore {

    private static final Logger log = LoggerFactory.getLogger(DefaultUrlCounterStore.class);

    private final Map<String, List<Visit>> store = new HashMap<>();

    @Override
    public Integer getAmountOfVisitsForHash(final String hash) {
        return getVisitsForHash(hash).size();
    }

    @Override
    public Integer getTotalAmountOfVisits() {
        Integer returnValue = 0;
        for (String hash: store.keySet()) {
            returnValue += getAmountOfVisitsForHash(hash);
        }
        return returnValue;
    }

    @Override
    public Integer getAmountOfDistinctHashesStored() {
        return store.keySet().size();
    }

    @Override
    public void addVisitToHash(final String hash, final String ip, final String userAgent) {
        final List<Visit> visits = getVisitsForHash(hash);
        visits.add(new Visit(ip, userAgent));
        store.put(hash, visits);
        log.debug ("A new visit has been added for the hash '{}' from the ip address '{}' and the user-agent '{}'", hash, ip, userAgent);
    }

    @Override
    public List<Visit> getVisitsForHash(final String hash) {
        final List<Visit> visits = store.get(hash);
        if (visits == null)
            return new ArrayList<>();
        return visits;
    }

    @Override
    public Map<String, Integer> getAmountOfVisitsPerHash() {
        final Map<String, Integer> returnValue = new HashMap<>();
        for (String key: store.keySet()) {
            returnValue.put(key, getAmountOfVisitsForHash(key));
        }
        return returnValue;
    }
}
