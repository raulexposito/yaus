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
	 * @param hash the hash
	 * @param ip the customer IP address
	 * @param userAgent the customer user agent
	 */
	void addVisitToHash(final String hash, final String ip, final String userAgent);

	/**
	 * Returns the visits stored for a short url
	 * @param hash the hash
	 * @return a list with the visits stored for a short url
	 */
	List<Visit> getVisitsForHash(final String hash);

	/**
	 * Returns the number of visits for a short url given
	 * @param hash the hash
	 * @return the number of visits
	 */
	Integer getAmountOfVisitsForHash(final String hash);

	/**
	 * Returns the total amount of visits
	 * @return the total amount of visits
	 */
	Integer getTotalAmountOfVisits();

	/**
	 * Returns the quantity of distinct hashes stored
	 * @return the quantity of distinct hashes stored
	 */
	Integer getAmountOfDistinctHashesStored();

	/**
	 * Returns a map with a relation between the hashes and the number of visits
	 * for each one
	 * @return a map with a relation between the hashes and the number of visits
	 * for each one
	 */
	Map<String, Integer> getAmountOfVisitsPerHash();
}
