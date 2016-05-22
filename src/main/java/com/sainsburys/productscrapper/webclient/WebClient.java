package com.sainsburys.productscrapper.webclient;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public interface WebClient {

    Document getDocument(String url);
    Connection.Response executeRequest(String url);

}
