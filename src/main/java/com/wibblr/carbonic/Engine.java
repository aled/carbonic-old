package com.wibblr.carbonic;

import com.wibblr.carbonic.api.*;

public class Engine implements Connection {

    public Engine() {

    }

    // Requests can come into the execute method on multiple threads
    public Response execute(Request request) {
        if (request instanceof ConnectRequest) {
            ConnectRequest req = ((ConnectRequest) request);
            ConnectResponse resp = new ConnectResponse(request.correlationId);
            resp.correlationId = request.correlationId;
            resp.serverProperties = new ConnectResponse.ServerProperties();
            resp.serverProperties.name = "carbonic";
            resp.serverProperties.version = "0.1";

            if ("admin".equals(req.credentials.username) && "super".equals(req.credentials.password)) {
                resp.status = 0;
                resp.connectionProperties = new ConnectResponse.ConnectionProperties();
                resp.connectionProperties.id = "abcdefg";
            } else {
                resp.status = 1;
            }
            return resp;
        }
        else if (request instanceof CreateTableRequest) {
            CreateTableRequest req = ((CreateTableRequest) request);
            // TODO: create the table...
            return new CreateTableResponse(request.correlationId, request.connectionId, 0);
        }
        else return new BadRequestResponse(request.correlationId, request.connectionId, 1);
    }
}