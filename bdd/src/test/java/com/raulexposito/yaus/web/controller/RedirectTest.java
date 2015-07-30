package com.raulexposito.yaus.web.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.StringContains.containsString;

public class RedirectTest extends AbstractTestController {

    @Test
    public void testUrlShortReturnsRedirectsToOriginalURL() throws IOException {
        final String shortUrl = createShortUrlForUrl ("http://raulexposito.com");
        expect().
            body(containsString("Raúl Expósito")).
            statusCode(HttpStatus.SC_OK).
        get(shortUrl);
    }

    @Test
    public void testNonexistentUrlShortReturnsNotFound() {
        expect().
            statusCode(HttpStatus.SC_NOT_FOUND).
        get("not_exist");
    }
}
