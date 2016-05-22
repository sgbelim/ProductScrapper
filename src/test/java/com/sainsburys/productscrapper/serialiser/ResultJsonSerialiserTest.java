package com.sainsburys.productscrapper.serialiser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.productscrapper.builders.ProductBuilder;
import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultJsonSerialiserTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void should_return_results_node_as_json_when_results_is_serialized() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        String actualResult = resultJsonSerialize.serialize(result);

        // Assert
        assertThat(actualResult).isNotNull();
        assertThatJson(actualResult).node("results").isPresent();
    }

    @Test
    public void should_return_same_product_in_json_as_passed_in_result() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().build();
        Product product2 = new ProductBuilder().build();
        Product product3 = new ProductBuilder().build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
        assertThatJson(result).node("results").isArray().ofLength(3);
    }

    @Test
    public void should_return_total() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().withPricePerUnit("22.9").build();
        Product product2 = new ProductBuilder().withPricePerUnit("32.9").build();
        Product product3 = new ProductBuilder().withPricePerUnit("42.9").build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
        assertThatJson(result).node("total").isEqualTo(98.7);
    }

    @Test
    public void should_convert_all_products_titles_to_json_with_correct_values() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().withTitle("title1").build();
        Product product2 = new ProductBuilder().withTitle("title2").build();
        Product product3 = new ProductBuilder().withTitle("title3").build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
        assertThatJson(result).node("results[0].title").isEqualTo("title1");
        assertThatJson(result).node("results[1].title").isEqualTo("title2");
        assertThatJson(result).node("results[2].title").isEqualTo("title3");
    }

    @Test
    public void should_convert_all_products_description_to_json_with_correct_values() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().withDescription("description 1").build();
        Product product2 = new ProductBuilder().withDescription("description 2").build();
        Product product3 = new ProductBuilder().withDescription("description 3").build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
        assertThatJson(result).node("results[0].description").isEqualTo("description 1");
        assertThatJson(result).node("results[1].description").isEqualTo("description 2");
        assertThatJson(result).node("results[2].description").isEqualTo("description 3");
    }

    @Test
    public void should_convert_all_products_size_to_json_with_correct_values() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().withPageSize(10.1).build();
        Product product2 = new ProductBuilder().withPageSize(20.2).build();
        Product product3 = new ProductBuilder().withPageSize(30).build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
        assertThatJson(result).node("results[0].size").isEqualTo("10.1Kb");
        assertThatJson(result).node("results[1].size").isEqualTo("20.2Kb");
        assertThatJson(result).node("results[2].size").isEqualTo("30Kb");
    }

    @Test
    public void should_convert_all_products_unit_price_to_json_with_correct_values() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        Product product1 = new ProductBuilder().withPricePerUnit("12.20").build();
        Product product2 = new ProductBuilder().withPricePerUnit("112.20").build();
        Product product3 = new ProductBuilder().withPricePerUnit("212.20").build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
        assertThatJson(result).node("results[0].unit_price").isEqualTo("12.2");
        assertThatJson(result).node("results[1].unit_price").isEqualTo("112.2");
        assertThatJson(result).node("results[2].unit_price").isEqualTo("212.2");
    }

    @Test
    public void should_return_no_products_when_productRepository_returns_no_products() {

        // Arrange
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mapper);

        // Act
        String actualResult = resultJsonSerialize.serialize(null);

        // Assert
        assertThat(actualResult).isEmpty();
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_RuntimeException_when_error_serializing_Result()  throws Exception {

        // Arrange
        ObjectMapper mockedMapper = mock(ObjectMapper.class);
        ResultSerialiser resultJsonSerialize = new ResultJsonSerialiser(mockedMapper);

        Product product1 = new ProductBuilder().withPricePerUnit("12.20").build();
        Product product2 = new ProductBuilder().withPricePerUnit("112.20").build();
        Product product3 = new ProductBuilder().withPricePerUnit("212.20").build();

        List<Product> products = asList(product1, product2, product3);
        Result result = new Result(products);

        when(mockedMapper.writeValueAsString(anyObject())).thenThrow(new JsonProcessingException("") {});

        // Act
        resultJsonSerialize.serialize(result);

        // Assert
    }
}