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

    @Override
    public void run(String... strings) {

        Result results = resultsFacade.getResults(SCRAPE_URL);

        reporter.report(results);
    }
}
