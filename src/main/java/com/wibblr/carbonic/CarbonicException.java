package com.wibblr.carbonic;

public class CarbonicException extends RuntimeException {
    public CarbonicException(String message) {
        super(message);
    }

    public CarbonicException(String message, Throwable cause) {
        super(message, cause);
    }
}
