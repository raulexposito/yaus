package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.service.UrlShortenerService;
import com.raulexposito.yaus.service.exception.InvalidURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlShortenerController {

    private static final String SHORTEN_URL = "/s/shorten";

    @Autowired
    private UrlShortenerService urlShortenerService;

    @RequestMapping(value = SHORTEN_URL, method = RequestMethod.POST)
    public @ResponseBody String shortenUrl(@RequestParam("url") final String url) throws InvalidURLException {
        return urlShortenerService.generate(url);
    }
}
