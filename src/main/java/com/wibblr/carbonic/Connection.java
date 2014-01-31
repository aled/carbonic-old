package com.wibblr.carbonic;

import com.wibblr.carbonic.api.Request;
import com.wibblr.carbonic.api.Response;

public interface Connection {
    Response execute(Request request);
}
