package com.sainsburys.productscrapper.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.sainsburys.productscrapper.constants.Constants.SCRAPE_URL;

@Component
public class ProductScrapeRunner implements CommandLineRunner {

    @Autowired
    private ResultsFacade resultsFacade;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run(String... strings) throws JsonProcessingException {

        Result results = resultsFacade.getResults(SCRAPE_URL);

        String resultAsJson = mapper.writeValueAsString(results);

        System.out.println(resultAsJson);
    }
}
