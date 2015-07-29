package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;

/**
 * The purpose of this class is just to relate the short urls to the original
 * ones
 */
public interface UrlMatcherStore {

	/**
	 * Relates the short url to the original one
	 * 
	 * @param shortUrl
	 *            the short url
	 * @param url
	 *            the original one
	 */
	void relateShortUrlToUrl(final String shortUrl, final String url);

	/**
	 * Returns the original url related to the short url
	 * 
	 * @param shortUrl
	 *            the short url
	 * @return the original url
	 * @throws ShortURLNotFoundException
	 *             if the short url cannot be found
	 */
	String getUrlFromShortUrl(final String shortUrl)
			throws ShortURLNotFoundException;
}
