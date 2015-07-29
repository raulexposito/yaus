package com.raulexposito.yaus.persistence.dao;

public interface UrlCounterStore {

	Integer getAmountOfVisitsForShortUrl(final String shortUrl);

	Integer getTotalAmountOfVisits();

	Integer getAmountOfDistinctShortUrlsStored();

	void addVisitForShortUrl(final String shortUrl, final String ip,
			final String userAgent);
}
