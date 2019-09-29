package com.foodie.order.service;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaService {

  private final KafkaProducer<String, String> kafkaProducer;

  public KafkaService(String brokerUrl) {

    var serializer = StringSerializer.class.getName();
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializer);
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer);

    this.kafkaProducer = new KafkaProducer<>(properties);
  }

  public void publish(String topic, String key, String value) {
    ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

    kafkaProducer.send(record, new Callback() {
      @Override
      public void onCompletion(RecordMetadata recordMetadata, Exception e){

        if(e != null) {
          System.out.println("Error: " + e);
        } else {
          System.out.println(recordMetadata);
        }
      }
    });

  }
}
