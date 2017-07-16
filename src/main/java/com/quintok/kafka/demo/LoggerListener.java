package com.quintok.kafka.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LoggerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerListener.class);
    private final ObjectMapper objectMapper;

    @Autowired
    public LoggerListener(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
    public void handle(final ChatMessage message) throws JsonProcessingException {
        LOGGER.info(objectMapper.writeValueAsString(message));
    }

    @KafkaListener(topics = "other-test", containerFactory = "kafkaListenerOtherMessageContainerFactory")
    public void handleOther(final OtherMessage message) throws JsonProcessingException {
        LOGGER.info("{}", objectMapper.writeValueAsString(message));
    }
}
