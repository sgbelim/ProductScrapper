package com.sainsburys.productscrapper.parser;

import com.sainsburys.productscrapper.webclient.WebClient;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class HtmlParserImpl implements Parser {

    protected static final String ALL_PRODUCT_INFO_WRAPPERS = "div.product";
    protected static final String CONTENT_LENGTH = "Content-Length";
    protected static final String PRODUCT_DETAILS = "div.productText p";
    protected static final int KB_DIVIDER = 1024;

    @Autowired
    private WebClient webClient;

    @Override
    public Elements getAllProducts(String url) {

        Document document = webClient.getDocument(url);
        Elements elements = document.select(ALL_PRODUCT_INFO_WRAPPERS);

        return elements;
    }

    @Override
    public double getPageSize(String detailsUrl) {

        Connection.Response response = webClient.executeRequest(detailsUrl);
        String contentLength = response.header(CONTENT_LENGTH);
        double pageSize = contentLength == null ? Double.valueOf("0.0") : (Double.valueOf(contentLength) / KB_DIVIDER);

        return pageSize;
    }

    @Override
    public String getProductDescription(String detailsUrl) {

        Document document = webClient.getDocument(detailsUrl);
        Elements elements = document.select(PRODUCT_DETAILS);
        Element first = elements.first();

        String description = (first == null) ? EMPTY : first.text();

        return description;
    }
}
