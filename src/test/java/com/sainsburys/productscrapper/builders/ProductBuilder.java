package com.sainsburys.productscrapper.builders;

import com.sainsburys.productscrapper.model.Product;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ProductBuilder {

    private String title;
    private String pricePerUnit;
    private double pageSize;
    private String description;

    public ProductBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder withPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public ProductBuilder withPageSize(double pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Product build() {
        Product product = new Product(this.title, EMPTY, this.pricePerUnit, this.pageSize, this.description);
        return product;
    }
}
