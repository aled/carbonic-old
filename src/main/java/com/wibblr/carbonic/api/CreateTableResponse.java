package com.wibblr.carbonic.api;

public class CreateTableResponse extends Response {
    public CreateTableResponse(String correlationId, String connectionId, int status) {
        super(correlationId, connectionId, status);
    }
}
