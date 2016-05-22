package com.sainsburys.productscrapper.parser;

import com.sainsburys.productscrapper.webclient.WebClient;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.sainsburys.productscrapper.constants.Constants.SCRAPE_URL;
import static com.sainsburys.productscrapper.parser.HtmlParserImpl.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HtmlParserImplTest {

    @Mock
    private WebClient webClient;

    @Mock
    private Document document;

    @Mock
    private Elements elements;

    @Mock
    private Connection.Response response;

    @InjectMocks
    private Parser htmlParser = new HtmlParserImpl();

    @Test
    public void should_get_all_products() {

        // Arrange
        when(webClient.getDocument(anyString())).thenReturn(document);
        when(document.select(ALL_PRODUCT_INFO_WRAPPERS)).thenReturn(elements);

        // Act
        Elements allProducts = htmlParser.getAllProducts(SCRAPE_URL);

        // Assert
        verify(webClient).getDocument(anyString());
        verify(document).select(ALL_PRODUCT_INFO_WRAPPERS);

        assertThat(allProducts).isSameAs(elements);
    }

    @Test
    public void should_get_page_size() {

        // Arrange
        String detailsUrl = "www.sainsbury.com/products";

        when(webClient.executeRequest(detailsUrl)).thenReturn(response);
        when(response.header(CONTENT_LENGTH)).thenReturn("1521546211");

        // Act
        double pageSize = htmlParser.getPageSize(detailsUrl);

        // Assert
        verify(webClient).executeRequest(detailsUrl);
        verify(response).header(CONTENT_LENGTH);

        assertThat(pageSize).isEqualTo(1485884.9716796875);
    }

    @Test
    public void should_return_zero_as_page_size_if_content_length_is_null() {

        // Arrange
        String detailsUrl = "www.sainsbury.com/products";

        when(webClient.executeRequest(detailsUrl)).thenReturn(response);
        when(response.header(CONTENT_LENGTH)).thenReturn(null);

        // Act
        double pageSize = htmlParser.getPageSize(detailsUrl);

        // Assert
        verify(webClient).executeRequest(detailsUrl);
        verify(response).header(CONTENT_LENGTH);

        assertThat(pageSize).isZero();
    }

    @Test
    public void should_get_product_description() {

        // Arrange
        String detailsUrl = "www.sainsbury.com/products";
        Element element = new Element(Tag.valueOf("div"), "some content");
        element.prepend("some description");

        when(webClient.getDocument(anyString())).thenReturn(document);
        when(document.select(PRODUCT_DETAILS)).thenReturn(elements);
        when(elements.first()).thenReturn(element);

        // Act
        String description = htmlParser.getProductDescription(detailsUrl);

        // Assert
        verify(webClient).getDocument(anyString());
        verify(document).select(PRODUCT_DETAILS);

        assertThat(description).isEqualTo("some description");
    }

    @Test
    public void should_return_empty_as_product_description_when_details_element_not_found() {

        // Arrange
        String detailsUrl = "www.sainsbury.com/products";

        when(webClient.getDocument(anyString())).thenReturn(document);
        when(document.select(PRODUCT_DETAILS)).thenReturn(elements);
        when(elements.first()).thenReturn(null);

        // Act
        String description = htmlParser.getProductDescription(detailsUrl);

        // Assert
        assertThat(description).isEmpty();
    }

}