package com.sainsburys.productscrapper.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sainsburys.productscrapper.builders.ProductBuilder;
import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductScrapeRunnerTest {

    @Mock
    private ResultsFacade resultsFacade;


    @InjectMocks
    private ProductScrapeRunner productScrapeRunner = new ProductScrapeRunner();

    @Test
    public void should_call_resultsFacade_to_get_results() throws JsonProcessingException {

        // Arrange
        Product product1 = new ProductBuilder().withPageSize(10.1).build();
        Product product2 = new ProductBuilder().withPageSize(20.2).build();
        Product product3 = new ProductBuilder().withPageSize(30).build();
        List<Product> products = asList(product1, product2, product3);

        Result result = new Result(products);

        when(resultsFacade.getResults(anyString())).thenReturn(result);

        // Act
        productScrapeRunner.run();

        // Assert
        verify(resultsFacade).getResults(anyString());

    }

}