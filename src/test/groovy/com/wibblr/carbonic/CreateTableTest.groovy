package com.wibblr.carbonic

import com.wibblr.carbonic.api.serialization.JsonSerializer
import com.wibblr.carbonic.transport.TcpServer
import com.wibblr.carbonic.transport.Transport
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.junit.Test

import static org.junit.Assert.assertEquals

class CreateTableTest {
    @Test
    public void CreateTable() throws Exception {

        // Start the server
        Engine engine = new Engine();

        // could have other transports, e.g. REST, XML/HTTP, api buffers/TCP
        Transport transport = new TcpServer(engine, new JsonSerializer(), 39873).startTransport();

        // Connect to the socket:
        Socket socket = new Socket("127.0.0.1", 39873);
        PrintWriter pw = new PrintWriter(new PrintStream(socket.getOutputStream()), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        def json = new JsonBuilder()

        json {
            request 'connect'
            correlation_id '123'
            client_properties {
                name 'test'
                version '0.1'
            }
            credentials {
                username 'admin'
                password 'super'
            }
        }

        json.println(pw)
        def actualResponse = br.readLine();
        def connectionId = new JsonSlurper().parseText(actualResponse).connection_properties.id

        json {
            response 'connect'
            correlation_id '123'
            status 0
            server_properties {
                name 'carbonic'
                version '0.1'
            }
            connection_properties {
                id connectionId
            }
        }
        def expectedResponse = json.toString()

        assertEquals(expectedResponse, actualResponse)

        // All messages other than 'connect' must include the connection id,
        // so that the server can service requests from different connections fairly

        // The following is specified as an object instead of a closure, as the primary key
        // was deserialized as a string, not an array with one vale
        json([
            request: 'create_table',
            correlation_id: '456',
            connection_id: connectionId,
            table_properties: [
                name: 'Product',
                columns: [
                    [
                        name: 'Id',
                        type: 'Int32'
                    ],
                    [
                        name: 'Name',
                        type: 'String',
                        max_length: 30,
                        nullable: false
                    ]
                ],
                primary_key: ['Id']
            ]
        ])
        json.println(pw)

        json {
            response 'create_table'
            correlation_id '456'
            connection_id connectionId
            status 0
        }
        expectedResponse = json.toString()
        actualResponse = br.readLine();

        assertEquals(expectedResponse, actualResponse)

        transport.shutdown();
    }
}
