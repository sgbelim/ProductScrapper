package com.sainsburys.productscrapper.webclient;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsoupWebClientTest {

    @Test
    public void should_return_document_when_getting_document_with_valid_url() {

        // Arrange
        WebClient webClient = new JsoupWebClient();
        String url = "http://www.yahoo.com";

        // Act
        Document document = webClient.getDocument(url);

        // Assert
        assertThat(document).isNotNull();
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_RuntimeException_when_getting_document_with_invalid_url() {

        // Arrange
        WebClient webClient = new JsoupWebClient();
        String url = "someinvalidurl";

        // Act
        webClient.getDocument(url);

    }

    @Test
    public void should_return_valid_response_when_executing_request_with_valid_url() {

        // Arrange
        WebClient webClient = new JsoupWebClient();
        String url = "http://www.yahoo.com";

        // Act
        Connection.Response response = webClient.executeRequest(url);

        // Assert
        assertThat(response).isNotNull();

    }

    @Test(expected = RuntimeException.class)
    public void should_throw_RuntimeException_when_executing_request_with_invalid_url() {

        // Arrange
        WebClient webClient = new JsoupWebClient();
        String url = "someinvalidurl";

        // Act
        webClient.executeRequest(url);
    }
}