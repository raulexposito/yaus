package com.raulexposito.yaus.service;

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

    private UrlShortener UrlShortenerMock = Mockito.mock(UrlShortener.class);

    @Before
    public void before() {
        urlShortenerService.setUrlShortener(UrlShortenerMock);
    }

    @Test
    public void testShortUrlSuccessfullyGenerated() throws InvalidURLException {
        when(UrlShortenerMock.generate("whatever")).thenReturn("1234");
        Assert.assertEquals("The (limited) hash of 'whatever' must be '1234'",
                "1234",
                urlShortenerService.generate("whatever"));
    }

    @Test (expected = InvalidURLException.class)
    public void testShortUrlUnsuccessfullyGenerated() throws InvalidURLException {
        when(UrlShortenerMock.generate(anyString())).thenThrow(InvalidURLException.class);
        urlShortenerService.generate("whatever");
    }
}
