package com.foodie.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodie.order.api.Order;
import io.dropwizard.jackson.Jackson;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaService {

private final Properties kafkaProducerProperties;

  public final static String NEW_ORDERS_TOPIC = "NEW-ORDERS";
  public final static String INVALID_ORDERS_TOPIC = "INVALID-ORDERS";

  public KafkaService(String brokerUrl) {

    var serializer = StringSerializer.class.getName();
    kafkaProducerProperties = new Properties();
    kafkaProducerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
    kafkaProducerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializer);
    kafkaProducerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer);

  }

  public void publish(String topic, String key, String value) {

    var kafkaProducer = new KafkaProducer<String, String>(kafkaProducerProperties);

    ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

    kafkaProducer.send(record, (recordMetadata, e) -> {

      if(e != null) {
        System.out.println("Error: " + e);
      } else {
        System.out.println(recordMetadata);
      }
    });

  }

  public void publish(String topic, Order order) {

    try {
      var orderJsonString = Jackson.newObjectMapper().writer().forType(Order.class).writeValueAsString(order);
      publish(topic, order.getUuid(), orderJsonString);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Cannot convert Order to JSON: " + order);
    }
  }
}
