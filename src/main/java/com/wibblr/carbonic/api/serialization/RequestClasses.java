package com.wibblr.carbonic.api.serialization;

import com.wibblr.carbonic.api.ConnectRequest;
import com.wibblr.carbonic.api.CreateTableRequest;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "request"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ConnectRequest.class, name = "connect"),
    @JsonSubTypes.Type(value = CreateTableRequest.class, name = "create_table")
})
abstract class RequestClasses {
}