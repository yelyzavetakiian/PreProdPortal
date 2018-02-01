package json.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import consumer.PostConsumer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOGGER = Logger.getLogger(PostConsumer.class);

    private ObjectMapper getMapper() {
        return mapper;
    }

    public <T> T convertResponseToObject(String response, Class<T> tClass) {
        try {
            return getMapper().readValue(response, tClass);
        } catch (IOException e) {
            LOGGER.error("Can not parse response body to object", e);
            throw new IllegalArgumentException();
        }
    }

    public <T> List<T> convertResponseToListOfObjects(String response, Class<T> tClass) {
        try {
            return getMapper().readValue(response, getMapper().getTypeFactory().constructCollectionType(ArrayList.class, tClass));
        } catch (IOException e) {
            LOGGER.error("Can not parse response body to list of objects", e);
            throw new IllegalArgumentException();
        }
    }

    public String convertObjectToJson(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Can not parse object to JSON", e);
            throw new IllegalArgumentException();
        }
    }
}
