package ru.dkadyrov.stackoverflow.searcher.internal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class Marshaller {

    private final ObjectMapper mapper;

    public Marshaller() {
        mapper= new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T marshaling(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Json marshaling fail", e);
        }
    }
}
