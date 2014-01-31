package com.wibblr.carbonic.transport;

import com.wibblr.carbonic.CarbonicException;
import com.wibblr.carbonic.Connection;
import com.wibblr.carbonic.api.serialization.Serializer;
import com.wibblr.carbonic.api.BadRequestResponse;
import com.wibblr.carbonic.api.Request;
import com.wibblr.carbonic.api.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TcpServer.java
 * <p/>
 * Implement a simple thread-per-connection TCP server
 */
public class TcpServer extends Thread implements Transport {
    private boolean stopRequested = false;
    private Connection connection;
    private Serializer serializer;
    private int port;
    private ServerSocket serverSocket;

    public TcpServer(Connection connection, Serializer serializer, int port) {
        this.connection = connection;
        this.serializer = serializer;
        this.port = port;
    }

    public TcpServer startTransport() throws Exception {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new Exception("Could not open server socket on port " + port, e);
        }
        start();
        return this;
    }

    // Start the server.
    public void run() {
        while (!stopRequested) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            new SocketHandler(socket).start();
        }
    }

    public void shutdown() {
        stopRequested = true;
    }

    private class SocketHandler extends Thread {
        private Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                while (!stopRequested) {
                    String line = r.readLine();
                    if (!stopRequested && line != null) {
                        try {
                            Request request = serializer.deserialize(line);
                            Response response = connection.execute(request);
                            pw.println(serializer.serialize(response));
                        } catch (CarbonicException e) {
                            Throwable t = e;
                            while (t != null) {
                                System.out.println(t.getMessage());
                                t = t.getCause();
                            }
                            String s = serializer.serialize(new BadRequestResponse(null, null, -1));
                            pw.println(s);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
