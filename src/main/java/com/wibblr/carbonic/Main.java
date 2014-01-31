package com.wibblr.carbonic;

import com.wibblr.carbonic.api.serialization.JsonSerializer;
import com.wibblr.carbonic.transport.TcpServer;
import com.wibblr.carbonic.transport.Transport;

public class Main {
    public static void main(String[] args) throws Exception {
        Engine engine = new Engine();

        // could have other transports, e.g. REST, XML/HTTP, api buffers/TCP
        Transport transport = new TcpServer(engine, new JsonSerializer(), 39871).startTransport();

        transport.shutdown();
    }
}
