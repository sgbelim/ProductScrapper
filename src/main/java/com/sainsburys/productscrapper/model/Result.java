package com.sainsburys.productscrapper.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private final List<Product> results;

    public Result(List<Product> results) {
        this.results = results;
    }

    public List<Product> getResults() {
        return results;
    }

    public BigDecimal getTotal() {

        if (results == null || results.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = results.stream().map(Product::getUnitPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return total;
    }
}
