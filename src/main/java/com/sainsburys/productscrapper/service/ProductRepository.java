package com.sainsburys.productscrapper.service;

import com.sainsburys.productscrapper.model.Product;

import java.util.List;

public interface ProductRepository {

   List<Product> getAll(String url);
}
