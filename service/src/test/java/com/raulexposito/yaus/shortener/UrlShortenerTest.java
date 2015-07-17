package com.raulexposito.yaus.shortener;

import org.junit.Assert;
import org.junit.Test;

public class UrlShortenerTest {

    private UrlShortener urlShortener = new UrlShortener();

    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    // http://testingdomain.com -> 7862655dd0ae9820157ba01b9e300579beb77045
    @Test
    public void testMyDomainURL() throws InvalidURLException {
        Assert.assertEquals("The (limited) hash of 'http://raulexposito.com' must be '9cc810cd'",
                "9cc810cd",
                urlShortener.generate("http://raulexposito.com"));
    }

    @Test
    public void testOsocoDomainURL() throws InvalidURLException {
        Assert.assertEquals("The (limited) hash of 'http://osoco.es' must be '1f206102'",
                "7862655d",
                urlShortener.generate("http://testingdomain.com"));
    }

    @Test
    public void testHashesHave8CharsLength() throws InvalidURLException {
        Assert.assertEquals("The (limited) hash must have a length of 8 chars",
                UrlShortener.MAX_LENGTH,
                urlShortener.generate("http://testingdomain.com").length());
    }
}
