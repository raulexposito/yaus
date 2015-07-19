package com.raulexposito.yaus.web.controller;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.IsEqual.equalTo;

// http://www.hascode.com/2011/10/testing-restful-web-services-made-easy-using-the-rest-assured-framework/
public class UrlShortenerControllerTest {

    @Test
    public void testCheckProofOfConceptReturns200() {
        expect().
            body(equalTo("proof of concept")).
            statusCode(200).
        when().
            with().
                parameters("url", "proof of concept").
        post("shorten");
    }

    @Test
    public void testCheckWrongMethod405() {
        expect().
            statusCode(405).
        when().
            with().
                parameters("url", "proof of concept").
        get("shorten");
    }
}
