package com.raulexposito.yaus.shortener;

import org.apache.commons.validator.routines.UrlValidator;

class Validator {

	private static final String[] VALID_PROTOCOLS = { "http", "https" };
	private static final boolean IS_VALID = true;

	private final UrlValidator urlValidator = new UrlValidator(VALID_PROTOCOLS);

	public boolean validate(final String url) throws InvalidURLException {
		if (!urlValidator.isValid(url))
			throw new InvalidURLException("The URL '" + url + "' is not valid");
		return IS_VALID;
	}
}
