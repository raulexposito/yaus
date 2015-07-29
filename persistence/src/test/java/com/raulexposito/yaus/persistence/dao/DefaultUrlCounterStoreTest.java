package com.raulexposito.yaus.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultUrlCounterStoreTest {

    UrlCounterStore urlCounterStore;

    @Before
    public void before () {
        urlCounterStore = new DefaultUrlCounterStore();
    }

    @Test
    public void thereAreNoVisitsForUrlAfterCreating () {
        Assert.assertEquals(new Integer(0), urlCounterStore.getAmountOfVisitsForShortUrl("non existent"));
    }

    @Test
    public void thereAreNoVisitsAfterCreating () {
        Assert.assertEquals(new Integer(0), urlCounterStore.getTotalAmountOfVisits());
    }

    @Test
    public void thereAreNoDistinctUrlsAfterCreating () {
        Assert.assertEquals(new Integer(0), urlCounterStore.getAmountOfDistinctUrlsStored());
    }

    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    // http://testingdomain.com -> 7862655dd0ae9820157ba01b9e300579beb77045
    @Test
    public void thereIsJustOneVisitAfterAddingJustOneVisit () {
        urlCounterStore.addVisitForShortUrl("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(1), urlCounterStore.getAmountOfVisitsForShortUrl("9cc810cd"));
        Assert.assertEquals(new Integer(1), urlCounterStore.getTotalAmountOfVisits());
        Assert.assertEquals(new Integer(1), urlCounterStore.getAmountOfDistinctUrlsStored());
    }

    @Test
    public void thereAreTwoVisitsOfTheSameShortUrl () {
        urlCounterStore.addVisitForShortUrl("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        urlCounterStore.addVisitForShortUrl("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(2), urlCounterStore.getAmountOfVisitsForShortUrl("9cc810cd"));
        Assert.assertEquals(new Integer(2), urlCounterStore.getTotalAmountOfVisits());
        Assert.assertEquals(new Integer(1), urlCounterStore.getAmountOfDistinctUrlsStored());
    }

    @Test
    public void thereAreNoVisitsOfDifferentShortUrl () {
        urlCounterStore.addVisitForShortUrl("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(0), urlCounterStore.getAmountOfVisitsForShortUrl("7862655d"));
    }

    @Test
    public void thereAreTwoDistinctUrlStored () {
        urlCounterStore.addVisitForShortUrl("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        urlCounterStore.addVisitForShortUrl("7862655d", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(2), urlCounterStore.getAmountOfDistinctUrlsStored());
    }
}