package com.wibblr.carbonic

class TransactionTest {
    def beginTransactionRequest = json {
        request 'begin_transaction'
        connection_id '123'
        correlation_id '234'
    }

    def beginTransactionResponse = json {
        response 'begin_transaction'
        correlation_id '234'
        status 0
        transaction_properties {
            id 'abc'
        }
    }.toString()
}
