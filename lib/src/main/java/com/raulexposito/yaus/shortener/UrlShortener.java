package com.raulexposito.yaus.shortener;

import org.apache.commons.codec.digest.DigestUtils;

public class UrlShortener {

	private static final int BEGINNING = 0;
	static final int MAX_LENGTH = 8;

	private Validator validator = new Validator();

	public String generate(final String url) throws InvalidURLException {

		int i = 0;

		validator.validate(url);
		return limit(DigestUtils.sha1Hex(url));
	}

	private String limit(final String hash) {
		return hash.substring(BEGINNING, MAX_LENGTH);
	}
}
