package com.raulexposito.yaus.web.controller;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.IsEqual.equalTo;

// http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/
public class ShortenerTest extends AbstractTestController {

    @Test
    public void testPostMyDomainReturns200andTheShortUrl() {
        expect().
            body(equalTo(DOMAIN + "9cc810cd")).
            statusCode(HttpStatus.SC_OK).
        when().
            with().
                parameters("url", "http://raulexposito.com").
        post("shortener");
    }

    @Test
    public void testPostInvalidURLReturns400() {
        expect().
            body(equalTo("The URL 'invalid' is not valid")).
            statusCode(HttpStatus.SC_BAD_REQUEST).
        when().
            with().
                parameters("url", "invalid").
        post("shortener");
    }

    @Test
    public void testWrongMethodReturns405() {
        expect().
            statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED).
        when().
            with().
                parameters("url", "using GET method instead of POST").
        get("shortener");
    }
}
