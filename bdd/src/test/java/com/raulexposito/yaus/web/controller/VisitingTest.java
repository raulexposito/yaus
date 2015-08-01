package com.raulexposito.yaus.web.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.StringContains.containsString;

public class VisitingTest extends AbstractTestController {

    @Test
    // http://raulexposito.com -> 9cc810cd
    public void testAddOneVisit() throws IOException {
        final String shortUrl = createShortUrlForUrl("http://raulexposito.com");
        visitShortUrl(shortUrl);

        expect().
            body(containsString("1")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount/9cc810cd");

        expect().
            body(containsString("1")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount/visits");

        expect().
            body(containsString("1")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount/hashes");

        expect().
            body(containsString("userAgent")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/visits/9cc810cd");

        expect().
            body(containsString("9cc810cd")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount");
    }

    @Test
    // http://duckduckgo.com -> 60453e41
    public void testAddTwoVisits() throws IOException {
        final String shortUrl = createShortUrlForUrl("http://duckduckgo.com");
        visitShortUrl(shortUrl);
        visitShortUrl(shortUrl);

        expect().
            body(containsString("2")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount/60453e41");

        expect().
            body(containsString("3")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount/visits");

        expect().
            body(containsString("2")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount/hashes");

        expect().
            body(containsString("userAgent")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/visits/60453e41");

        expect().
            body(containsString("60453e41")).
            statusCode(HttpStatus.SC_OK).
        get(DOMAIN + "/amount");
    }
}
