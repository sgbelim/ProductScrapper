package com.sainsburys.productscrapper.model;

import com.sainsburys.productscrapper.builders.ProductBuilder;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class ResultTest {

    @Test
    public void should_get_total_of_price_per_unit() {

        // Arrange
        Product product1 = new ProductBuilder().withPricePerUnit("10.0").build();
        Product product2 = new ProductBuilder().withPricePerUnit("20.9").build();
        Product product3 = new ProductBuilder().withPricePerUnit("55.1").build();

        List<Product> products = asList(product1, product2, product3);

        BigDecimal expectedTotal = new BigDecimal("86.0");

        Result result = new Result(products);

        // Act
        BigDecimal actualTotal = result.getTotal();

        // Assert
        assertThat(actualTotal).isEqualTo(expectedTotal);
    }

    @Test
    public void should_return_zero_for_total_if_null_products_passed() {

        // Arrange
        Result result = new Result(null);

        // Act
        BigDecimal actualTotal = result.getTotal();

        // Assert
        assertThat(actualTotal).isEqualTo(BigDecimal.ZERO);
    }


    @Test
    public void should_return_zero_for_total_if_empty_products_passed() {

        // Arrange
        Result result = new Result(asList());

        // Act
        BigDecimal actualTotal = result.getTotal();

        // Assert
        assertThat(actualTotal).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void should_return_zero_as_total_if_not_producct_have_price_per_unit_specified() {

        // Arrange
        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);

        Result result = new Result(products);

        // Act
        BigDecimal actualTotal = result.getTotal();

        // Assert
        assertThat(actualTotal).isZero();
    }
}