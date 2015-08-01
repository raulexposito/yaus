package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DefaultMatcherStore implements UrlMatcherStore {

    private static final Logger log = LoggerFactory.getLogger(DefaultMatcherStore.class);

    private final Map<String, String> match = new HashMap<>();

    @Override
    public void relateHashToUrl(final String hash, final String url) {
        match.put(hash, url);
        log.debug("The hash '{}' and the url '{}' have been related", hash, url);
    }

    @Override
    public String getUrlFromHash(final String hash) throws HashNotFoundException {
        final String url = match.get(hash);
        if (url == null) {
            log.warn("The hash '{}' doesn't exist", hash);
            throw new HashNotFoundException("The hash '" + hash + "' doesn't exist");
        }
        return url;
    }
}
