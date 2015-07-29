package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DefaultMatcherStore implements UrlMatcherStore {

    private final Map<String, String> match = new HashMap<>();

    @Override
    public void relateShortUrlToUrl(final String shortUrl, final String url) {
        match.put(shortUrl, url);
    }

    @Override
    public String getUrlFromShortUrl(final String shortUrl) throws ShortURLNotFoundException {
        final String url = match.get(shortUrl);
        if (url == null)
            throw new ShortURLNotFoundException("The short url '" + shortUrl + "' doesn't exist");
        return url;
    }
}
