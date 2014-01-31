package com.wibblr.carbonic.api.serialization;

import com.wibblr.carbonic.api.BadRequestResponse;
import com.wibblr.carbonic.api.ConnectResponse;
import com.wibblr.carbonic.api.CreateTableResponse;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "response")
@JsonSubTypes({
        @JsonSubTypes.Type( value = BadRequestResponse.class, name = "bad_request"),
        @JsonSubTypes.Type( value = CreateTableResponse.class, name = "create_table"),
        @JsonSubTypes.Type(value = ConnectResponse.class, name = "connect")})
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
abstract class ResponseClasses {
}

