package com.foodie.restaurant.service;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Properties;
import java.util.Set;

public class OrdersKafkaConsumer implements Runnable{

  private final Properties kafkaConsumerProperties;

  private final static String CONSUMER_GROUP = "RESTAURANT-CONSUMER-GROUP";
  private final static String CONSUMER_TOPIC = "NEW-ORDER";

  public OrdersKafkaConsumer(String brokerUrl) {

    var serializer = StringDeserializer.class.getName();
    kafkaConsumerProperties = new Properties();
    kafkaConsumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
    kafkaConsumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serializer);
    kafkaConsumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serializer);
    kafkaConsumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP);

  }



  @Override
  public void run() {
    var consumer = new KafkaConsumer<String, String>(kafkaConsumerProperties);
    consumer.subscribe(Set.of(CONSUMER_TOPIC));

    while (true) {

      ConsumerRecords<String, String> records = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
      if(!records.isEmpty()) {
        records.forEach(record -> {
          System.out.println(String.format("FOUND: [%s] %s - %s",record.topic(), record.key(), record.value()));
        });

        consumer.commitSync();
      }
    }

  }
}
