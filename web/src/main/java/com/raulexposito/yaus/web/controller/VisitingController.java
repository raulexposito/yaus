package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.persistence.dao.Visit;
import com.raulexposito.yaus.persistence.exception.ShortURLNotFoundException;
import com.raulexposito.yaus.service.UrlShortenerService;
import com.raulexposito.yaus.service.VisitingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class VisitingController {

    private static final Logger log = LoggerFactory.getLogger(VisitingController.class);

    @Autowired
    private VisitingService visitingService;

    @Autowired
    private UrlShortenerService urlShortenerService;

    @RequestMapping(value = "/s/visits/{shortUrl}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visit> visitsForShortUrl(@PathVariable("shortUrl") String shortUrl) throws ShortURLNotFoundException {
        final List<Visit> visits = visitingService.getVisitsForShortUrl(shortUrl);
        log.info("The visits for the short url '{}' have been requested with '{}' items", shortUrl, visits.size());
        return visits;
    }

    @RequestMapping(value = "/s/amount/{shortUrl}", method = RequestMethod.GET)
    public @ResponseBody Integer amountForShortUrl(@PathVariable("shortUrl") String shortUrl) throws ShortURLNotFoundException {
        final Integer amount = visitingService.getAmountOfVisitsForShortUrl(shortUrl);
        log.info("There are '{}' visits for the short url '{}'", amount, shortUrl);
        return amount;
    }

    @RequestMapping(value = "/s/amount/visits", method = RequestMethod.GET)
    public @ResponseBody Integer amountVisits() throws ShortURLNotFoundException {
        final Integer amount = visitingService.getTotalAmountOfVisits();
        log.info("There are '{}' visits in total", amount);
        return amount;
    }

    @RequestMapping(value = "/s/amount/shorturls", method = RequestMethod.GET)
    public @ResponseBody Integer amountShortUrls() throws ShortURLNotFoundException {
        final Integer amount = visitingService.getAmountOfDistinctShortUrlsStored();
        log.info("There are '{}' short urls in total", amount);
        return amount;
    }

    @RequestMapping(value = "/s/amount", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Relation> amount() throws ShortURLNotFoundException {
        final Map<String, Integer> amount = visitingService.getAmountOfVisitsPerShortUrl();
        final List<Relation> relations = generateRelations(amount);
        log.info("The relation between the short urls and the amount of visits have been requested with '{}' short urls", relations.size());
        return relations;
    }

    private List<Relation> generateRelations (final Map<String, Integer> amount) throws ShortURLNotFoundException {
        final List<Relation> relations = new ArrayList<>(amount.size());
        for (String shortUrl: amount.keySet()) {
            relations.add(new Relation(
                    urlShortenerService.generateTheShortUrlWithJustTheHash(shortUrl),
                    urlShortenerService.getUrlFromShortUrl(shortUrl),
                    amount.get(shortUrl)));
        }
        return relations;
    }
    
    @Getter
    @AllArgsConstructor
    class Relation {
        private String shortUrl;
        private String url;
        private Integer count;
    }
}