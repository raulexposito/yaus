package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.persistence.dao.Visit;
import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import com.raulexposito.yaus.service.VisitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class VisitingController {

    @Autowired
    private VisitingService visitingService;

    @RequestMapping(value = "/s/visits/{shortUrl}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visit> visitsForShortUrl(@PathVariable("shortUrl") String shortUrl) throws ShortURLNotFoundException {
        return visitingService.getVisitsForShortUrl(shortUrl);
    }

    @RequestMapping(value = "/s/amount/{shortUrl}", method = RequestMethod.GET)
    public @ResponseBody Integer amountForShortUrl(@PathVariable("shortUrl") String shortUrl) throws ShortURLNotFoundException {
        return visitingService.getAmountOfVisitsForShortUrl(shortUrl);
    }

    @RequestMapping(value = "/s/amount/visits", method = RequestMethod.GET)
    public @ResponseBody Integer amountVisits() throws ShortURLNotFoundException {
        return visitingService.getTotalAmountOfVisits();
    }

    @RequestMapping(value = "/s/amount/shorturls", method = RequestMethod.GET)
    public @ResponseBody Integer amountShortUrls() throws ShortURLNotFoundException {
        return visitingService.getAmountOfDistinctShortUrlsStored();
    }

    @RequestMapping(value = "/s/amount", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Integer> amount() throws ShortURLNotFoundException {
        return visitingService.getAmountOfVisitsPerShortUrl();
    }
}