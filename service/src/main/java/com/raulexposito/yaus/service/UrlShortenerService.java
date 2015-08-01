package com.raulexposito.yaus.service;

import com.raulexposito.yaus.persistence.dao.UrlMatcherStore;
import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
import com.raulexposito.yaus.service.exception.InvalidURLException;
import com.raulexposito.yaus.service.urlshortener.HashGenerator;
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

    private HashGenerator hashGenerator = new HashGenerator();

    void setHashGenerator(final HashGenerator hashGenerator) {
        this.hashGenerator = hashGenerator;
    }

    public String generate (final String url) throws InvalidURLException {
        log.debug("A short url has been requested for the url '{}'", url);
        final String hash = hashGenerator.generate(url);
        urlMatcherStore.relateHashToUrl(hash, url);
        return generateTheShortUrlFromTheHash(hash);
    }

    public String getUrlFromHash(final String hash) throws HashNotFoundException {
        return urlMatcherStore.getUrlFromHash(hash);
    }

    public String generateTheShortUrlFromTheHash(final String hash) {
        return DOMAIN + hash;
    }
}
