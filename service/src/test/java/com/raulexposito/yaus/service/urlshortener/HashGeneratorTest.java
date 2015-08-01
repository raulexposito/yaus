package com.raulexposito.yaus.service.urlshortener;

import com.raulexposito.yaus.service.exception.InvalidURLException;
import org.junit.Assert;
import org.junit.Test;

public class HashGeneratorTest {

    private HashGenerator hashGenerator = new HashGenerator();

    // http://www.sha1-online.com/
    // http://raulexposito.com -> 9cc810cdab40aae66568bcb2a0397a0ceb50f9b1
    // http://testingdomain.com -> 7862655dd0ae9820157ba01b9e300579beb77045
    @Test
    public void testMyDomainURL() throws InvalidURLException {
        Assert.assertEquals("The (limited) hash of 'http://raulexposito.com' must be '9cc810cd'",
                "9cc810cd",
                hashGenerator.generate("http://raulexposito.com"));
    }

    @Test
    public void testTestingDomainDomainURL() throws InvalidURLException {
        Assert.assertEquals("The (limited) hash of 'http://testingdomain.com' must be '7862655d'",
                "7862655d",
                hashGenerator.generate("http://testingdomain.com"));
    }

    @Test
    public void testHashesHave8CharsLength() throws InvalidURLException {
        Assert.assertEquals("The (limited) hash must have a length of 8 chars",
                HashGenerator.MAX_LENGTH,
                hashGenerator.generate("http://testingdomain.com").length());
    }
}
