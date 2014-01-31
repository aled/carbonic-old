package com.wibblr.carbonic.api;

public class ConnectRequest extends Request {

    public static class Credentials {
        public String username;
        public String password;
    }

    public static class ClientProperties {
        public String name;
        public String version;
    }

    public ClientProperties clientProperties;
    public Credentials credentials;
}


