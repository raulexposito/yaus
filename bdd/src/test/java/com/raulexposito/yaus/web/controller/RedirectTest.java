package com.raulexposito.yaus.web.controller;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.apache.http.client.methods.HttpPost;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.StringContains.containsString;

public class RedirectTest {

    @Test
    public void testUrlShortReturnsRedirectsToOriginalURL() throws IOException {
        final HttpClient client = HttpClients.createDefault();
        final HttpPost post = new HttpPost("http://localhost:8080/s/shorten");
        final List<NameValuePair> nameValuePairs = new ArrayList<>(1);
        nameValuePairs.add(new BasicNameValuePair("url", "http://raulexposito.com"));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        client.execute(post);

        expect().
            body(containsString("Raúl Expósito")).
            statusCode(HttpStatus.SC_OK).
        get("9cc810cd");
    }

    @Test
    public void testNonexistentUrlShortReturnsNotFound() {
        expect().
            statusCode(HttpStatus.SC_NOT_FOUND).
        get("not_exist");
    }
}
