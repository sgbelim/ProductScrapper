package com.sainsburys.productscrapper.serialiser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sainsburys.productscrapper.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * ResultSerialiser implementation to serialise the result as json
 */
public class ResultJsonSerialiser implements ResultSerialiser {

    private static final Logger LOG = LoggerFactory.getLogger(ResultJsonSerialiser.class);

    private final ObjectMapper mapper;

    public ResultJsonSerialiser(ObjectMapper objectMapper){
        this.mapper = objectMapper;
    }

    /**
     *
     * @param result
     * @return - Serialised object as string
     */
    @Override
    public String serialize(Result result) {

        if(result == null) {
            return EMPTY;
        }

        try {

            String resultAsJson = mapper.writeValueAsString(result);
            return resultAsJson;

        } catch (JsonProcessingException e) {
            LOG.error("Error serialising results to JSON", e);
            throw new RuntimeException(e);
        }
    }
}
