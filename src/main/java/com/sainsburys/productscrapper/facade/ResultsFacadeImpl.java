package com.sainsburys.productscrapper.facade;

import com.sainsburys.productscrapper.model.Product;
import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.service.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultsFacadeImpl implements ResultsFacade {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Result getResults(String url) {

        List<Product> products = productRepository.getAll(url);
        
        Result result = new Result(products);

        return result;
    }
}
