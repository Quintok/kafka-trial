package com.quintok.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@SpringBootApplication
public class Application {

    private final KafkaProperties properties;

    @Autowired
    public Application(KafkaProperties properties) {
        this.properties = properties;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ConsumerFactory<String, ChatMessage> kafkaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties(),
                null,
                new JsonDeserializer<>(ChatMessage.class));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, ChatMessage> kafkaListenerContainerFactory(ConsumerFactory<String, ChatMessage> kafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ChatMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, OtherMessage> kafkaOtherMessageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties(),
                null,
                new JsonDeserializer<>(OtherMessage.class));
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, OtherMessage> kafkaListenerOtherMessageContainerFactory(ConsumerFactory<String, OtherMessage> kafkaOtherMessageConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, OtherMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaOtherMessageConsumerFactory);
        return factory;
    }
}
