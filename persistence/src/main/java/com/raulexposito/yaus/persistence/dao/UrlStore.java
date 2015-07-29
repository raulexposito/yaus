package com.raulexposito.yaus.persistence.dao;

public interface UrlStore {

	Integer getAmountOfVisitsForUrl(final String url);

	Integer getTotalAmountOfVisits();

	Integer getAmountOfDistinctUrlsStored();
}
