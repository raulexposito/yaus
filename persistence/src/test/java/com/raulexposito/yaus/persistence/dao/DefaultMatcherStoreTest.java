package com.raulexposito.yaus.persistence.dao;

import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultMatcherStoreTest {

    UrlMatcherStore matcherStore;

    @Before
    public void before () {
        matcherStore = new DefaultMatcherStore();
    }

    @Test (expected = HashNotFoundException.class)
    public void testThereIsNoUrlForThisHash() throws HashNotFoundException {
        matcherStore.getUrlFromHash("nonexistent");
    }

    @Test
    public void testThereIsOneUrlForThisHash() throws HashNotFoundException {
        matcherStore.relateHashToUrl("9cc810cd", "http://raulexposito.com");
        Assert.assertEquals("http://raulexposito.com", matcherStore.getUrlFromHash("9cc810cd"));
    }
}