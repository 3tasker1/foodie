package com.foodie.restaurant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodie.restaurant.api.Order;
import io.dropwizard.jackson.Jackson;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaService {

  private final Properties kafkaProducerProperties;

  public final static String NEW_ORDER_TOPIC = "NEW-ORDERS";
  public final static String INVALID_ORDER_TOPIC = "INVALID-ORDERS";

  public KafkaService(String brokerUrl) {

    var serializer = StringSerializer.class.getName();
    kafkaProducerProperties = new Properties();
    kafkaProducerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
    kafkaProducerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializer);
    kafkaProducerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer);

  }

  private static void onCompletion(RecordMetadata recordMetadata, Exception e) {
    if (e != null) {
      System.out.println("Error: " + e);
    } else {
      System.out.println(recordMetadata);
    }
  }

  public void publish(String topic, String key, String value) {

    var kafkaProducer = new KafkaProducer<String, String>(kafkaProducerProperties);

    ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

    kafkaProducer.send(record, KafkaService::onCompletion);

  }

  public void publish(String topic, String key, Order value) {

    String orderJsonString = null;
    try {
      orderJsonString = Jackson.newObjectMapper().writer().forType(Order.class).writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Cannot convert order to JSON: " + value);
    }

    publish(topic, key, orderJsonString);

  }
}
