package com.raulexposito.yaus.service;

import com.raulexposito.yaus.persistence.dao.UrlMatcherStore;
import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
import com.raulexposito.yaus.service.exception.InvalidURLException;
import com.raulexposito.yaus.service.urlshortener.HashGenerator;
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
public class HashGeneratorServiceTest {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @Autowired
    private UrlMatcherStore urlMatcherStore;

    private HashGenerator hashGeneratorMock = Mockito.mock(HashGenerator.class);

    @Before
    public void before() {
        urlShortenerService.setHashGenerator(new HashGenerator());
    }

    @Test
    public void testHashSuccessfullyGenerated() throws InvalidURLException {
        urlShortenerService.setHashGenerator(hashGeneratorMock);
        when(hashGeneratorMock.generate("whatever")).thenReturn("1234");
        Assert.assertEquals("The (limited) hash of 'whatever' must be '" + urlShortenerService.generateTheShortUrlFromTheHash("1234") + "'",
                urlShortenerService.generateTheShortUrlFromTheHash("1234"),
                urlShortenerService.generate("whatever"));
    }

    @Test (expected = InvalidURLException.class)
    public void testHashUnsuccessfullyGenerated() throws InvalidURLException {
        urlShortenerService.setHashGenerator(hashGeneratorMock);
        when(hashGeneratorMock.generate(anyString())).thenThrow(InvalidURLException.class);
        urlShortenerService.generate("whatever");
    }

    @Test (expected = HashNotFoundException.class)
    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    public void tesUrlAndHashAreNotRelatedUntilHashIsCreated() throws HashNotFoundException {
        Assert.assertEquals("http://raulexposito.com", urlMatcherStore.getUrlFromHash(urlShortenerService.generateTheShortUrlFromTheHash("9cc810cd")));
    }

    @Test
    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    public void tesUrlAndHashAreRelatedAfterHashIsCreated() throws HashNotFoundException, InvalidURLException {
        urlShortenerService.generate("http://raulexposito.com");
        Assert.assertEquals("http://raulexposito.com", urlMatcherStore.getUrlFromHash("9cc810cd"));
    }
}
