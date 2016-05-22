package com.sainsburys.productscrapper.webclient;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * WebClient Implementation to connect to the scrape url
 */
@Component
public class JsoupWebClient implements WebClient {

    private static final Logger LOG = LoggerFactory.getLogger(JsoupWebClient.class);

    /**
     *
     * @param url - The Url for scrapping
     * @return Document - the page document as  Jsoup Document
     */
    @Override
    public Document getDocument(String url) {

        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.error("Error connecting to url : {} ", url, e);
        }

        return document;
    }


    /**
     *
     * @param url - The Url for scrapping
     * @return Jsoup Response object
     */
    @Override
    public Connection.Response executeRequest(String url) {

        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).execute();
        } catch (IOException e) {
            LOG.error("Error connecting to url", e);
        }

        return response;
    }
}
