package com.sainsburys.productscrapper.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsburys.productscrapper.facade.ResultsFacade;
import com.sainsburys.productscrapper.facade.ResultsFacadeImpl;
import com.sainsburys.productscrapper.parser.HtmlParserImpl;
import com.sainsburys.productscrapper.parser.Parser;
import com.sainsburys.productscrapper.serialiser.ResultJsonSerialiser;
import com.sainsburys.productscrapper.serialiser.ResultSerialiser;
import com.sainsburys.productscrapper.service.HttpHtmlProductRepository;
import com.sainsburys.productscrapper.service.ProductRepository;
import com.sainsburys.productscrapper.webclient.JsoupWebClient;
import com.sainsburys.productscrapper.webclient.WebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new HttpHtmlProductRepository();
    }

    @Bean
    public ResultsFacade resultsFacade() {
        return new ResultsFacadeImpl();
    }

    @Bean
    public Parser htmlParser() {
        return new HtmlParserImpl();
    }

    @Bean
    public WebClient webClient() {
        return new JsoupWebClient();
    }

    @Bean
    public ResultSerialiser jsonSerializer() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return new ResultJsonSerialiser(mapper);
    }
}
