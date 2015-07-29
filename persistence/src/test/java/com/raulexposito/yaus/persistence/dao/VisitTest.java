package com.raulexposito.yaus.persistence.dao;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;

public class VisitTest {

    @Test
    public void testTheIPIsStored () {
        final Visit visit = new Visit("127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals("127.0.0.1", visit.getIp());
    }

    @Test
    public void testTheUserAgentIsStored () {
        final Visit visit = new Visit("127.0.0.1", "Mozilla/5.0");
        Assert.assertEquals("Mozilla/5.0", visit.getUserAgent());
    }

    @Test
    // Thu, 01 Jan 2015 12:30:00 GMT
    public void testTheDateIsStored () {
        final LocalDateTime localDateTime = new LocalDateTime(2015,
                DateTimeConstants.JANUARY, 1, 12, 30, 0);
        final Visit visit = new Visit("127.0.0.1", "Mozilla/5.0", localDateTime);
        Assert.assertEquals(visit.getYear(), new Integer(2015));
        Assert.assertSame(visit.getMonth(), DateTimeConstants.JANUARY);
        Assert.assertSame(visit.getDay(), 1);
        Assert.assertSame(visit.getHour(), 12);
        Assert.assertSame(visit.getMinute(), 30);
        Assert.assertSame(visit.getSecond(), 0);
        Assert.assertSame(visit.getWeekday(), DateTimeConstants.THURSDAY);
    }
}