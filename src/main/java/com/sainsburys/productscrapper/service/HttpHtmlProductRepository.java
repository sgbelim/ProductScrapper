package com.sainsburys.productscrapper.service;

import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * ProductRepository Implementation to scrape the website
 */
@Service
public class HttpHtmlProductRepository implements ProductRepository {

    protected static final String TITLE = "div.productInfo a";
    protected static final String DETAILS_URL = "div.productInfo a";
    protected static final String HREF = "href";
    protected static final String PRICE_PER_UNIT = "pricePerUnit";

    @Autowired
    private Parser htmlParser;

    /**
     *
     * @param url - The Url for scrapping
     * @return  - List of Products
     */
    @Override
    public List<Product> getAll(String url) {

        Elements allProducts = htmlParser.getAllProducts(url);

        if (allProducts == null) {
            return asList();
        }

        List<Product> products = allProducts.stream().map(p -> {
            String title = p.select(TITLE).text();
            String detailsUrl = p.select(DETAILS_URL).attr(HREF);
            String pricePerUnit = p.getElementsByClass(PRICE_PER_UNIT).get(0).ownText();

            double pageSize = htmlParser.getPageSize(detailsUrl);
            String description = htmlParser.getProductDescription(detailsUrl);

            Product product = new Product(title, detailsUrl, pricePerUnit, pageSize, description);

            return product;

        }).collect(Collectors.toList());

        return products;
    }

}
