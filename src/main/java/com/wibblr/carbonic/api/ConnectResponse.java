package com.wibblr.carbonic.api;

public class ConnectResponse extends Response {
    public ServerProperties serverProperties;
    public ConnectionProperties connectionProperties;

    public ConnectResponse(String correlationId) {
        super(correlationId, null, 0);
    }

    public static class ConnectionProperties {
        public String id;
    }

    public static class ServerProperties {
        public String name;
        public String version;
    }
}
