package com.sainsburys.productscrapper.runner;

import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.reporter.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.sainsburys.productscrapper.constants.Constants.SCRAPE_URL;

/**
 * Command Line Runner to scrape the webpage and return the result
 */
@Component
public class ProductScrapeRunner implements CommandLineRunner {

    @Autowired
    private ResultsFacade resultsFacade;

    @Autowired
    private Reporter reporter;

    /**
     *
     * @param args - Takes the url from argument if specified otherwise uses the default url
     */
    @Override
    public void run(String... args) {

        String scrapeUrl = (args.length == 0) ? SCRAPE_URL : args[0];

        Result results = resultsFacade.getResults(scrapeUrl);

        reporter.report(results);
    }
}
