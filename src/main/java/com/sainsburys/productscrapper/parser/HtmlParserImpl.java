package com.sainsburys.productscrapper.parser;

import com.sainsburys.productscrapper.webclient.WebClient;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Parser Implementation to scrape and parse the website
 */
@Component
public class HtmlParserImpl implements Parser {

    protected static final String ALL_PRODUCT_INFO_WRAPPERS = "div.product";
    protected static final String CONTENT_LENGTH = "Content-Length";
    protected static final String PRODUCT_DETAILS = "div.productText p";
    protected static final int KB_DIVIDER = 1024;

    @Autowired
    private WebClient webClient;

    /**
     *
     * @param url - The Url for scrapping
     * @return Elements - All product information Jsoup Elements
     */
    @Override
    public Elements getAllProducts(String url) {

        Document document = webClient.getDocument(url);
        Elements elements = document.select(ALL_PRODUCT_INFO_WRAPPERS);

        return elements;
    }

    /**
     *
     * @param detailsUrl - The details Url for scrapping
     * @return - The size of the page
     */
    @Override
    public double getPageSize(String detailsUrl) {

        Connection.Response response = webClient.executeRequest(detailsUrl);
        String contentLength = response.header(CONTENT_LENGTH);
        double pageSize = contentLength == null ? Double.valueOf("0.0") : (Double.valueOf(contentLength) / KB_DIVIDER);

        return pageSize;
    }

    /**
     *
     * @param detailsUrl  - The details Url for scrapping
     * @return - Product description text
     */
    @Override
    public String getProductDescription(String detailsUrl) {

        Document document = webClient.getDocument(detailsUrl);
        Elements elements = document.select(PRODUCT_DETAILS);
        Element first = elements.first();

        String description = (first == null) ? EMPTY : first.text();

        return description;
    }
}
