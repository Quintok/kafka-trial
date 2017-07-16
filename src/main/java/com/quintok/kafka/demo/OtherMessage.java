package com.quintok.kafka.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OtherMessage {
    private final String message;

    @JsonCreator
    public OtherMessage(@JsonProperty("message") final String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
