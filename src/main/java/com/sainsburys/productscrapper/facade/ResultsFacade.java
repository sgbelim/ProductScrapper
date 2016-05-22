package com.sainsburys.productscrapper.facade;

import com.sainsburys.productscrapper.model.Result;

public interface ResultsFacade {

    Result getResults(String url);
}
