package com.raulexposito.yaus.service.urlshortener;

import com.raulexposito.yaus.service.exception.InvalidURLException;
import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

	private static final int BEGINNING = 0;
	static final int MAX_LENGTH = 8;

	private Validator validator = new Validator();

	public String generate(final String url) throws InvalidURLException {
		validator.validate(url);
		return limit(DigestUtils.sha1Hex(url));
	}

	private String limit(final String hash) {
		return hash.substring(BEGINNING, MAX_LENGTH);
	}
}
