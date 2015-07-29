package com.raulexposito.yaus.persistence.dao;

public interface UrlCounterStore {

	Integer getAmountOfVisitsForShortUrl(final String url);

	Integer getTotalAmountOfVisits();

	Integer getAmountOfDistinctUrlsStored();

	void addVisitForShortUrl(final String url, final String ip,
			final String userAgent);
}
