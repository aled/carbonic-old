package com.wibblr.carbonic.api.serialization;

import com.wibblr.carbonic.CarbonicException;
import com.wibblr.carbonic.api.Request;
import com.wibblr.carbonic.api.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class JsonSerializer implements Serializer {

    public Request deserialize(String text) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new UnderscoreNamingStrategy());
        mapper.getDeserializationConfig().addMixInAnnotations(Request.class, RequestClasses.class);
        try {
            return mapper.readValue(text, Request.class);
        } catch (Exception e) {
            throw new CarbonicException("Failed to deserialize", e);
        }
    }

    public String serialize(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(new UnderscoreNamingStrategy());
        mapper.getSerializationConfig().addMixInAnnotations(Response.class, ResponseClasses.class);
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            throw new CarbonicException("Failed to serialize", e);
        }
    }
}
