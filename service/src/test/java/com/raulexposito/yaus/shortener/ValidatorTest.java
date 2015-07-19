package com.raulexposito.yaus.shortener;

import org.junit.Test;

public class ValidatorTest {

    private Validator validator = new Validator();

    @Test
    public void checkHttpURLIsValid() throws InvalidURLException {
        validator.validate("http://testingdomain.com");
    }

    @Test
    public void checkHttpURLWithOneParameterIsValid() throws InvalidURLException {
        validator.validate("http://testingdomain.com?param1=value");
    }

    @Test
    public void checkHttpURLWithTwoParametersIsValid() throws InvalidURLException {
        validator.validate("http://testingdomain.com?param1=value&param2=value");
    }

    @Test
    public void checkHttpsURLIsValid() throws InvalidURLException {
        validator.validate("https://testingdomain.com");
    }

    @Test (expected = InvalidURLException.class)
    public void checkURLAnotherProtocolIsInvalid() throws InvalidURLException {
        validator.validate("ftp://testingdomain.com");
    }

    @Test (expected = InvalidURLException.class)
    public void checkURLWithoutProtocolIsInvalid() throws InvalidURLException {
        validator.validate("testingdomain.com");
    }

    @Test (expected = InvalidURLException.class)
    public void checkURLWithoutHostIsInvalid() throws InvalidURLException {
        validator.validate("http://.com");
    }

    @Test (expected = InvalidURLException.class)
    public void checkURLWithoutDomainIsInvalid() throws InvalidURLException {
        validator.validate("http://testingdomain");
    }
}
