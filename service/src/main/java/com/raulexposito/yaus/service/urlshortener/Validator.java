package com.raulexposito.yaus.service.urlshortener;

import com.raulexposito.yaus.service.exception.InvalidURLException;
import org.apache.commons.validator.routines.UrlValidator;

class Validator {

	private static final String[] VALID_PROTOCOLS = { "http", "https" };
	private final UrlValidator urlValidator = new UrlValidator(VALID_PROTOCOLS);

	public void validate(final String url) throws InvalidURLException {
		if (!urlValidator.isValid(url))
			throw new InvalidURLException("The URL '" + url + "' is not valid");
	}
}
