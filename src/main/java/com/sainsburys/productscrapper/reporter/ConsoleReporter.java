package com.sainsburys.productscrapper.reporter;

import com.sainsburys.productscrapper.model.Result;
import com.sainsburys.productscrapper.serialiser.ResultSerialiser;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleReporter implements Reporter {

    @Autowired
    private ResultSerialiser resultSerialiser;

    @Override
    public void report(Result result) {

        String results = resultSerialiser.serialize(result);

        System.out.println(results);
    }

}
