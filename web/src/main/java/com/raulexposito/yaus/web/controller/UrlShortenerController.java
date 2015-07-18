package com.raulexposito.yaus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlShortenerController {

    private static final String SHORTEN_URL = "/shorten";

    @RequestMapping(value = SHORTEN_URL, method = RequestMethod.POST)
    public @ResponseBody String shortenUrl(@RequestParam("url") final String url) {
        return url;
    }
}
