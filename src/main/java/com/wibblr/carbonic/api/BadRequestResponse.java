package com.wibblr.carbonic.api;

public class BadRequestResponse extends Response {
    public BadRequestResponse(String correlationId, String connectionId, int status) {
        super(correlationId, connectionId, status);
    }
}
