package com.raulexposito.yaus.web.controller;

import com.raulexposito.yaus.persistence.dao.Visit;
import com.raulexposito.yaus.persistence.exception.HashNotFoundException;
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

    @RequestMapping(value = "/visits/{hash}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Visit> visitsForHash(@PathVariable("hash") String hash) throws HashNotFoundException {
        final List<Visit> visits = visitingService.getVisitsForHash(hash);
        log.info("The visits for the short url '{}' have been requested with '{}' items", hash, visits.size());
        return visits;
    }

    @RequestMapping(value = "/amount/{hash}", method = RequestMethod.GET)
    public @ResponseBody Integer amountForHash(@PathVariable("hash") String hash) throws HashNotFoundException {
        final Integer amount = visitingService.getAmountOfVisitsForHash(hash);
        log.info("There are '{}' visits for the hash '{}'", amount, hash);
        return amount;
    }

    @RequestMapping(value = "/amount/visits", method = RequestMethod.GET)
    public @ResponseBody Integer amountVisits() throws HashNotFoundException {
        final Integer amount = visitingService.getTotalAmountOfVisits();
        log.info("There are '{}' visits in total", amount);
        return amount;
    }

    @RequestMapping(value = "/amount/hashes", method = RequestMethod.GET)
    public @ResponseBody Integer amountHashes() throws HashNotFoundException {
        final Integer amount = visitingService.getAmountOfDistinctHashesStored();
        log.info("There are '{}' hashes in total", amount);
        return amount;
    }

    @RequestMapping(value = "/amount", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Relation> amount() throws HashNotFoundException {
        final Map<String, Integer> amount = visitingService.getAmountOfVisitsPerHash();
        final List<Relation> relations = generateRelations(amount);
        log.info("The relation between the short urls and the amount of visits have been requested with '{}' short urls", relations.size());
        return relations;
    }

    private List<Relation> generateRelations (final Map<String, Integer> amount) throws HashNotFoundException {
        final List<Relation> relations = new ArrayList<>(amount.size());
        for (String hash: amount.keySet()) {
            relations.add(new Relation(
                    urlShortenerService.generateTheShortUrlFromTheHash(hash),
                    urlShortenerService.getUrlFromHash(hash),
                    amount.get(hash)));
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