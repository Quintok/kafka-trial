package com.quintok.kafka.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RestController
public class TimeSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeSource.class);


    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public TimeSource(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/default")
    public void timerMessageSource() {
        LOGGER.info("Received message");
        ChatMessage chatMessage = new ChatMessage(randomAlphanumeric(10), System.currentTimeMillis());
        kafkaTemplate.send("test", chatMessage);
    }

    @GetMapping("/other")
    public void sendOther() {
        LOGGER.info("Received message");
        OtherMessage otherMessage = new OtherMessage(randomAlphanumeric(10));
        kafkaTemplate.send("other-test", otherMessage);
    }
}
