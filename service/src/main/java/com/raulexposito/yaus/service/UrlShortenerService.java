package com.raulexposito.yaus.service;

import com.raulexposito.yaus.persistence.dao.UrlMatcherStore;
import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import com.raulexposito.yaus.service.exception.InvalidURLException;
import com.raulexposito.yaus.service.urlshortener.UrlShortener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    private static final Logger log = LoggerFactory.getLogger(UrlShortenerService.class);

    private static final String DOMAIN = "http://localhost:8080/";

    @Autowired
    private UrlMatcherStore urlMatcherStore;

    private UrlShortener urlShortener = new UrlShortener();

    void setUrlShortener (final UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    public String generate (final String url) throws InvalidURLException {
        log.debug("A short url has been requested for the url '{}'", url);
        final String shortUrl = urlShortener.generate(url);
        urlMatcherStore.relateShortUrlToUrl(shortUrl, url);
        return getDomain() + shortUrl;
    }

    public String getUrlFromShortUrl (final String shortUrl) throws ShortURLNotFoundException {
        return urlMatcherStore.getUrlFromShortUrl(shortUrl);
    }

    public String getDomain() {
        return DOMAIN;
    }
}
