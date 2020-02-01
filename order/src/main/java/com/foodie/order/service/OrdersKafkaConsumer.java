package com.foodie.order.service;

import com.foodie.order.api.Order;
import io.dropwizard.jackson.Jackson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.Set;

public class OrdersKafkaConsumer implements Runnable{

  private final Properties kafkaConsumerProperties;

  private final static String CONSUMER_GROUP = "INVALID-ORDER-CONSUMER-GROUP";
  private final Thread thread;

  private final OrdersService ordersService;

  public OrdersKafkaConsumer(String brokerUrl, OrdersService ordersService) {
    this.ordersService = ordersService;

    var serializer = StringDeserializer.class.getName();
    kafkaConsumerProperties = new Properties();
    kafkaConsumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
    kafkaConsumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serializer);
    kafkaConsumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serializer);
    kafkaConsumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP);

    thread = new Thread(this);
    thread.start();

  }



  @Override
  public void run() {
    var consumer = new KafkaConsumer<String, String>(kafkaConsumerProperties);
    consumer.subscribe(Set.of(KafkaService.INVALID_ORDERS_TOPIC));

    while (true) {

      try {

        ConsumerRecords<String, String> records = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
        if (!records.isEmpty()) {
          records.forEach(record -> {
            var orderOptional = ordersService.findOrder(record.key());
            orderOptional.ifPresent(ordersService::markOrderAsInvalid);
          });

          consumer.commitSync();
        }
      } catch (Exception e) {
        System.out.println(e);
      }

    }

  }
}
