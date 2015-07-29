package com.raulexposito.yaus.service;

import com.raulexposito.yaus.persistence.dao.UrlMatcherStore;
import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import com.raulexposito.yaus.service.exception.InvalidURLException;
import com.raulexposito.yaus.service.urlshortener.UrlShortener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:basic-test-context.xml" })
public class UrlShortenerServiceTest {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Autowired
    private UrlMatcherStore urlMatcherStore;

    private UrlShortener UrlShortenerMock = Mockito.mock(UrlShortener.class);

    @Before
    public void before() {
        urlShortenerService.setUrlShortener(new UrlShortener());
    }

    @Test
    public void testShortUrlSuccessfullyGenerated() throws InvalidURLException {
        urlShortenerService.setUrlShortener(UrlShortenerMock);
        when(UrlShortenerMock.generate("whatever")).thenReturn("1234");
        Assert.assertEquals("The (limited) hash of 'whatever' must be '1234'",
                "1234",
                urlShortenerService.generate("whatever"));
    }

    @Test (expected = InvalidURLException.class)
    public void testShortUrlUnsuccessfullyGenerated() throws InvalidURLException {
        urlShortenerService.setUrlShortener(UrlShortenerMock);
        when(UrlShortenerMock.generate(anyString())).thenThrow(InvalidURLException.class);
        urlShortenerService.generate("whatever");
    }

    @Test (expected = ShortURLNotFoundException.class)
    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    public void tesUrlAndShortUrlAreNotRelatedUntilShortUrlIsCreated() throws ShortURLNotFoundException {
        Assert.assertEquals("http://raulexposito.com", urlMatcherStore.getUrlFromShortUrl("9cc810cd"));
    }


    @Test
    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    public void tesUrlAndShortUrlAreRelatedAfterShortUrlIsCreated() throws ShortURLNotFoundException, InvalidURLException {
        urlShortenerService.generate("http://raulexposito.com");
        Assert.assertEquals("http://raulexposito.com", urlMatcherStore.getUrlFromShortUrl("9cc810cd"));
    }
}
