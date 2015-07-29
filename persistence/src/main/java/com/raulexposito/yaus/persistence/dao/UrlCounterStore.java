package com.raulexposito.yaus.persistence.dao;

import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is just to store the visits for every
 * short url
 */
public interface UrlCounterStore {

	/**
	 * Adds a new visit to the short url with some metadata
	 * @param shortUrl the short url
	 * @param ip the customer IP address
	 * @param userAgent the customer user agent
	 */
	void addVisitForShortUrl(final String shortUrl, final String ip, final String userAgent);

	/**
	 * Returns the visits stored for a short url
	 * @param shortUrl the short url
	 * @return a list with the visits stored for a short url
	 */
	List<Visit> getVisitsForShortUrl (final String shortUrl);

	/**
	 * Returns the number of visits for a short url given
	 * @param shortUrl the short url
	 * @return the number of visits
	 */
	Integer getAmountOfVisitsForShortUrl(final String shortUrl);

	/**
	 * Returns the total amount of visits
	 * @return the total amount of visits
	 */
	Integer getTotalAmountOfVisits();

	/**
	 * Returns the quantity of distinct short urls stored
	 * @return the quantity of distinct short urls stored
	 */
	Integer getAmountOfDistinctShortUrlsStored();

	/**
	 * Returns a map with a relation between the short urls and the number of visits
	 * for each one
	 * @return a map with a relation between the short urls and the number of visits
	 * for each one
	 */
	Map<String, Integer> getAmountOfVisitsPerShortUrl ();
}
