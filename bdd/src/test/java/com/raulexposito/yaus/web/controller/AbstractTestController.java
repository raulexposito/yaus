package com.raulexposito.yaus.web.controller;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// http://www.journaldev.com/7146/apache-httpclient-example-to-send-get-post-http-requests
public abstract class AbstractTestController {

	protected static final String DOMAIN = "http://localhost:8080/";

	protected String createShortUrlForUrl (final String url) throws IOException {
        final CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost post = new HttpPost(DOMAIN + "/s/shortener");
        final List<NameValuePair> nameValuePairs = new ArrayList<>(1);
        nameValuePairs.add(new BasicNameValuePair("url", url));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        final CloseableHttpResponse httpResponse = client.execute(post);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        final StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }

        reader.close();
        client.close();
        return response.toString();
    }

	protected void visitShortUrl(String shortUrl) throws IOException {
		final CloseableHttpClient client = HttpClients.createDefault();
		final HttpGet get = new HttpGet(shortUrl);
		client.execute(get);
		client.close();
	}
}
