package com.sainsburys.productscrapper.webclient;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsoupWebClient implements WebClient {

    private static final Logger LOG = LoggerFactory.getLogger(JsoupWebClient.class);

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
