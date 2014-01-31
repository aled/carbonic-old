package com.wibblr.carbonic.api;

public class Response {
    public String correlationId;
    public String connectionId;
    public int status;

    public Response(String correlationId, String connectionId, int status) {
        this.correlationId = correlationId;
        this.connectionId = connectionId;
        this.status = status;
    }
}
