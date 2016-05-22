package com.sainsburys.productscrapper.service;

import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static com.sainsburys.productscrapper.constants.Constants.SCRAPE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpHtmlProductRepositoryTest {

    @Mock
    private Parser htmlParser;

    @InjectMocks
    private ProductRepository httpHtmlProductRepository = new HttpHtmlProductRepository();

    @Test
    public void should_call_html_parser_to_get_all_products() {

        // Arrange
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(null);

        // Act
        httpHtmlProductRepository.getAll(SCRAPE_URL);

        // Assert
        verify(htmlParser).getAllProducts(SCRAPE_URL);
    }

    @Test
    public void should_return_empty_when_parser_returns_null() {

        // Arrange
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(null);

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);

        // Assert
        assertThat(products).isEmpty();
    }

    @Test
    public void should_return_products_when_parser_not_return_products() {

        // Arrange
        Elements elements = getProductElements();

        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(elements);

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);

        // Assert
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(1);
    }

    @Test
    public void should_populate_title_from_html() {

        // Arrange
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(elements);

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);
        Product firstProduct = products.get(0);

        // Assert
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getTitle()).isEqualTo("Sainsbury's Apricot Ripe & Ready x5");
    }

    @Test
    public void should_populate_details_url_from_html() {

        // Arrange
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(elements);

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);
        Product firstProduct = products.get(0);

        // Assert
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getDetailsUrl())
                .isEqualTo(
                        "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html");
    }

    @Test
    public void should_populate_price_per_unit_from_html() {

        // Arrange
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(elements);
        BigDecimal expectedUnitPrice = new BigDecimal("3.50");

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);
        Product firstProduct = products.get(0);

        // Assert
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getUnitPrice()).isEqualTo(expectedUnitPrice);
    }

    @Test
    public void should_set_page_size_on_product() {

        // Arrange
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(elements);
        when(htmlParser.getPageSize(anyString())).thenReturn(10.13);

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);
        Product firstProduct = products.get(0);

        // Assert
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getSize()).isEqualTo("10.13Kb");
    }

    @Test
    public void should_set_description_on_product() {

        // Arrange
        Elements elements = getProductElements();
        when(htmlParser.getAllProducts(SCRAPE_URL)).thenReturn(elements);
        when(htmlParser.getProductDescription(anyString())).thenReturn("some product description");

        // Act
        List<Product> products = httpHtmlProductRepository.getAll(SCRAPE_URL);
        Product firstProduct = products.get(0);

        // Assert
        assertThat(firstProduct).isNotNull();
        assertThat(firstProduct.getDescription()).isEqualTo("some product description");
    }

    private Elements getProductElements() {

        String productsHtml = "<div class=\"product \"> \n"
                + " <div class=\"productInner\"> \n"
                + "  <div class=\"productInfoWrapper\"> \n"
                + "   <div class=\"productInfo\"> \n"
                + "    <h3> <a href=\"http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-ripe---ready-320g.html\"> Sainsbury's Apricot Ripe &amp; Ready x5 <img src=\"http://c2.sainsburys.co.uk/wcsstore7.11.1.161/SainsburysStorefrontAssetStore/wcassets/product_images/media_7572754_M.jpg\" alt=\"\"> </a> </h3> \n"
                + "\n"
                + "      <div class=\"pricing\"> \n"
                + "       <p class=\"pricePerUnit\"> &amp;pound3.50<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> \n"
                + "       <p class=\"pricePerMeasure\">&amp;pound0.70<abbr title=\"per\">/</abbr><abbr title=\"each\"><span class=\"pricePerMeasureMeasure\">ea</span></abbr> </p> \n"
                + "      </div> \n" + "      \n" + "    </div> \n" + "   </div> \n" + "  </div> \n" + " </div> \n";

        Document document = Jsoup.parse(productsHtml);

        return document.select("div.product");
    }

}