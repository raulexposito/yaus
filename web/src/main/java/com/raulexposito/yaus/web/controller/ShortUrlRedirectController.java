package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
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

    @RequestMapping(value = "/{hash:[0-9|a-f]{8}}", method = RequestMethod.GET)
    public String redirect(@PathVariable("hash") String hash, final HttpServletRequest request) throws HashNotFoundException {
        visitingService.addVisit(hash, request.getRemoteAddr(), request.getHeader("user-agent"));
        final String url = urlShortenerService.getUrlFromHash(hash);
        log.info("Redirecting from hash '{}' to '{}'", hash, url);
        return "redirect:" + url;
    }
}
