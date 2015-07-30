package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import com.raulexposito.yaus.service.UrlShortenerService;
import com.raulexposito.yaus.service.VisitingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShortUrlRedirectController {

    private static final Logger log = LoggerFactory.getLogger(ShortUrlRedirectController.class);

    @Autowired
    private VisitingService visitingService;

    @Autowired
    private UrlShortenerService urlShortenerService;

    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public String redirect(@PathVariable("shortUrl") String shortUrl, final HttpServletRequest request) throws ShortURLNotFoundException {
        visitingService.addVisit(shortUrl, request.getRemoteAddr(), request.getHeader("user-agent"));
        final String url = urlShortenerService.getUrlFromShortUrl(shortUrl);
        log.info("Redirecting from '{}' to '{}'", shortUrl, url);
        return "redirect:" + url;
    }
}
