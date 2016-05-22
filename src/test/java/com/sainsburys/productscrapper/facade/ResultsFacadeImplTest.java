package com.sainsburys.productscrapper.facade;

import com.sainsburys.productscrapper.builders.ProductBuilder;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.service.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultsFacadeImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ResultsFacade resultsFacade = new ResultsFacadeImpl();

    private String url = "http://www.someurl.com/somepage/";

    @Test
    public void should_call_product_service_get_all_to_return_products() {

        // Arrange
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);
        when(productRepository.getAll(url)).thenReturn(products);

        // Act
        resultsFacade.getResults(url);

        // Assert
        verify(productRepository).getAll(url);
    }

    @Test
    public void should_return_same_results_when_productRepository_returns_some_products() {

        // Arrange
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();
        List<Product> products = asList(product1, product2, product3);

        when(productRepository.getAll(url)).thenReturn(products);

        // Act
        Result result = resultsFacade.getResults(url);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getResults().size()).isEqualTo(3);
        assertThat(result.getResults()).isSameAs(products);
    }

    @Test
    public void should_return_no_products_when_productRepository_returns_no_products() {

        // Arrange
        when(productRepository.getAll(url)).thenReturn(null);

        // Act
        Result result = resultsFacade.getResults(url);

        // Assert
        assertThat(result.getResults()).isNull();
    }

}