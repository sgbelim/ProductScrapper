package com.sainsburys.productscrapper.serialiser;

import com.sainsburys.productscrapper.model.Result;

public interface ResultSerialiser {
    String serialize(Result result);
}
