package com.sainsburys.productscrapper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class Product {

    private static final String NON_NUMERIC_REGEX = "[^\\d.]";
    private static final String KB = "Kb";

    private final String title;

    @JsonIgnore
    private final String detailsUrl;
    private final double pageSize;

    @JsonIgnore
    private final String pricePerUnit;

    private final String description;

    public Product(String title, String detailsUrl, String pricePerUnit, double pageSize, String description) {
        this.title = title;
        this.detailsUrl = detailsUrl;
        this.pricePerUnit = pricePerUnit;
        this.pageSize = pageSize;
        this.description = description;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public String getTitle() {
        return title;
    }

    @JsonProperty("unit_price")
    public BigDecimal getUnitPrice() {

        if (pricePerUnit != null) {
            String pricePerUnitWithoutStrings = pricePerUnit.replaceAll(NON_NUMERIC_REGEX, EMPTY);
            return new BigDecimal(pricePerUnitWithoutStrings);
        }

        return BigDecimal.ZERO;
    }

    public String getSize() {
        return new DecimalFormat("#.##").format(pageSize) + KB;
    }

    public String getDescription() {
        return description;
    }
}
