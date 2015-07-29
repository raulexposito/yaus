package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultMatcherStoreTest {

    UrlMatcherStore matcherStore;

    @Before
    public void before () {
        matcherStore = new DefaultMatcherStore();
    }

    @Test (expected = ShortURLNotFoundException.class)
    public void testThereIsNoUrlForThisShortURL () throws ShortURLNotFoundException {
        matcherStore.getUrlFromShortUrl("nonexistent");
    }

    @Test
    public void testThereIsOneUrlForThisShortURL () throws ShortURLNotFoundException {
        matcherStore.relateShortUrlToUrl("9cc810cd", "http://raulexposito.com");
        Assert.assertEquals("http://raulexposito.com", matcherStore.getUrlFromShortUrl("9cc810cd"));
    }
}