package com.foodie.order.service;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaService {

private final Properties kafkaProducerProperties;

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
}
