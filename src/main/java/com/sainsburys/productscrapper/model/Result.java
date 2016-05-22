package com.sainsburys.productscrapper.model;


import java.math.BigDecimal;
import java.util.List;

public class Result {

    private final List<Product> results;

    public Result(List<Product> results) {
        this.results = results;
    }

    public List<Product> getResults() {
        return results;
    }

    /**
     * calculates the total unit price
     * @return - Total UnitPrice
     */
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
