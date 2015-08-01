package com.raulexposito.yaus.persistence.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class DefaultUrlCounterStoreTest {

    UrlCounterStore urlCounterStore;

    @Before
    public void before () {
        urlCounterStore = new DefaultUrlCounterStore();
    }

    @Test
    public void testThereAreNoVisitsForUrlAfterCreating () {
        Assert.assertEquals(new Integer(0), urlCounterStore.getAmountOfVisitsForHash("nonexistent"));
    }

    @Test
    public void testThereAreNoVisitsAfterCreating () {
        Assert.assertEquals(new Integer(0), urlCounterStore.getTotalAmountOfVisits());
    }

    @Test
    public void testThereAreNoDistinctUrlsAfterCreating () {
        Assert.assertEquals(new Integer(0), urlCounterStore.getAmountOfDistinctHashesStored());
    }

    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    // http://testingdomain.com -> 7862655dd0ae9820157ba01b9e300579beb77045
    @Test
    public void testThereIsJustOneVisitAfterAddingJustOneVisit () {
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(1), urlCounterStore.getAmountOfVisitsForHash("9cc810cd"));
        Assert.assertEquals(new Integer(1), urlCounterStore.getTotalAmountOfVisits());
        Assert.assertEquals(new Integer(1), urlCounterStore.getAmountOfDistinctHashesStored());
    }

    @Test
    public void testThereAreTwoVisitsOfTheSameHash() {
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(2), urlCounterStore.getAmountOfVisitsForHash("9cc810cd"));
        Assert.assertEquals(new Integer(2), urlCounterStore.getTotalAmountOfVisits());
        Assert.assertEquals(new Integer(1), urlCounterStore.getAmountOfDistinctHashesStored());
    }

    @Test
    public void testThereAreNoVisitsOfDifferentHashes() {
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(0), urlCounterStore.getAmountOfVisitsForHash("7862655d"));
    }

    @Test
    public void testThereAreTwoDistinctUrlStored () {
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        urlCounterStore.addVisitToHash("7862655d", "127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals(new Integer(2), urlCounterStore.getAmountOfDistinctHashesStored());
    }

    @Test
    public void testRelationOfHashesAndAmountVisits() {
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        urlCounterStore.addVisitToHash("9cc810cd", "127.0.0.1", "Mozilla/5.0");
        urlCounterStore.addVisitToHash("7862655d", "127.0.0.1", "Mozilla/5.0");
        final Map<String, Integer> relation = urlCounterStore.getAmountOfVisitsPerHash();
        Assert.assertEquals(new Integer(2), relation.get("9cc810cd"));
        Assert.assertEquals(new Integer(1), relation.get("7862655d"));
    }
}