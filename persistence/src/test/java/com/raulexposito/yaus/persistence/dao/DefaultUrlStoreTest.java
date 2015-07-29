package com.raulexposito.yaus.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultUrlStoreTest {

    UrlStore urlStore;

    @Before
    public void before () {
        urlStore = new DefaultUrlStore();
    }

    @Test
    public void thereAreNoVisitsForUrlAfterCreating () {
        Assert.assertEquals(new Integer(0), urlStore.getAmountOfVisitsForUrl("non existent"));
    }

    @Test
    public void thereAreNoVisitsAfterCreating () {
        Assert.assertEquals(new Integer(0), urlStore.getTotalAmountOfVisits());
    }

    @Test
    public void thereAreNoDistinctUrlsAfterCreating () {
        Assert.assertEquals(new Integer(0), urlStore.getAmountOfDistinctUrlsStored());
    }
}