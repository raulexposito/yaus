package com.raulexposito.yaus.service;

import com.raulexposito.yaus.service.exception.InvalidURLException;
import com.raulexposito.yaus.service.urlshortener.UrlShortener;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    private UrlShortener urlShortener = new UrlShortener();

    void setUrlShortener (final UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    public String generate (final String url) throws InvalidURLException {
        return urlShortener.generate(url);
    }
}
