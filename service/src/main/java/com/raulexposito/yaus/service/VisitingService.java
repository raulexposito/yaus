package com.raulexposito.yaus.service;

import com.raulexposito.yaus.persistence.dao.UrlCounterStore;
import com.raulexposito.yaus.persistence.dao.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisitingService {

    @Autowired
    private UrlCounterStore urlCounterStore;

    public void addVisit (final String hash, final String ip, final String userAgent) {
        urlCounterStore.addVisitToHash(hash, ip, userAgent);
    }

    public List<Visit> getVisitsForHash(final String hash) {
        return urlCounterStore.getVisitsForHash(hash);
    }

    public Integer getAmountOfVisitsForHash(final String hash) {
        return urlCounterStore.getAmountOfVisitsForHash(hash);
    }

    public Integer getTotalAmountOfVisits() {
        return urlCounterStore.getTotalAmountOfVisits();
    }

    public Integer getAmountOfDistinctHashesStored() {
        return urlCounterStore.getAmountOfDistinctHashesStored();
    }

    public Map<String, Integer> getAmountOfVisitsPerHash() {
        return urlCounterStore.getAmountOfVisitsPerHash();
    }
}
