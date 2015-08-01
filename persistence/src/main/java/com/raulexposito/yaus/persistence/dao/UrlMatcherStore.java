package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.HashNotFoundException;

/**
 * The purpose of this class is just to relate the short urls to the original
 * ones
 */
public interface UrlMatcherStore {

	/**
	 * Relates the hash to the original url
	 * 
	 * @param hash
	 *            the hash
	 * @param url
	 *            the url
	 */
	void relateHashToUrl(final String hash, final String url);

	/**
	 * Returns the url related to hash
	 * 
	 * @param hash
	 *            the hash
	 * @return the url
	 * @throws HashNotFoundException
	 *             if the short url cannot be found
	 */
	String getUrlFromHash(final String hash) throws HashNotFoundException;
}
