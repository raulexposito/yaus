package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;

public interface UrlMatcherStore {

    void relateShortUrlToUrl(final String shortUrl, final String url);

    String getUrlFromShortUrl(final String shortUrl) throws ShortURLNotFoundException;
}
