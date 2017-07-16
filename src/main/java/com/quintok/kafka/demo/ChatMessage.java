package com.quintok.kafka.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMessage {
    private final String contents;
    private final long time;

    @JsonCreator
    public ChatMessage(@JsonProperty("contents") String contents, @JsonProperty("time") long time) {
        this.contents = contents;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getContents() {
        return contents;
    }
}
