package com.wibblr.carbonic.api.serialization;

import com.wibblr.carbonic.api.Request;
import com.wibblr.carbonic.api.Response;

public interface Serializer {
    Request deserialize(String request);
    String serialize(Response response);
}
