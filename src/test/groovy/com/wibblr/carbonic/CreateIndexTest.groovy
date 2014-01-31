package com.wibblr.carbonic

class CreateIndexTest {

    def createIndexRequest = json {
        request 'create_index'
        correlation_id '456'
        connection_id '123'
        index_properties {
            table_name 'Product'
            name 'ByName'
            unique true
            columns (
                    'Name'
            )
        }
    }

}
