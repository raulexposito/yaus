package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.service.UrlShortenerService;
import com.raulexposito.yaus.service.exception.InvalidURLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlShortenerController {

    private static final Logger log = LoggerFactory.getLogger(UrlShortenerController.class);

    private static final String SHORTENER_URL = "/s/shortener";

    @Autowired
    private UrlShortenerService urlShortenerService;

    @RequestMapping(value = SHORTENER_URL, method = RequestMethod.POST)
    public @ResponseBody String shortenerUrl(@RequestParam("url") final String url) throws InvalidURLException {
        final String shortUrl = urlShortenerService.generate(url);
        log.info("The short url '{}' has been generated for '{}'", shortUrl, url);
        return shortUrl;
    }
}
