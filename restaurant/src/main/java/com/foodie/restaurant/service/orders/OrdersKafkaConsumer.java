package com.foodie.restaurant.service.orders;

import com.foodie.restaurant.api.Order;
import com.foodie.restaurant.service.KafkaService;
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

  private final static String CONSUMER_GROUP = "RESTAURANT-CONSUMER-GROUP";
  private final OrderHandlingService orderHandlingService;
  private final Thread thread;

  public OrdersKafkaConsumer(String brokerUrl, OrderHandlingService orderHandlingService) {
    this.orderHandlingService = orderHandlingService;

    var serializer = StringDeserializer.class.getName();
    kafkaConsumerProperties = new Properties();
    kafkaConsumerProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
    kafkaConsumerProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serializer);
    kafkaConsumerProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serializer);
    kafkaConsumerProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP);

    thread = new Thread(this);
    thread.run();

  }



  @Override
  public void run() {
    var consumer = new KafkaConsumer<String, String>(kafkaConsumerProperties);
    consumer.subscribe(Set.of(KafkaService.NEW_ORDER_TOPIC));

    while (true) {

      try {

        ConsumerRecords<String, String> records = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
        if (!records.isEmpty()) {
          records.forEach(record -> {
            System.out.println(String.format("FOUND: [%s] %s - %s", record.topic(), record.key(), record.value()));
            var orderString = record.value();
            Order order = null;
            try {
              order = Jackson.newObjectMapper().readerFor(Order.class).readValue(orderString);
              System.out.println("Deserialised Order to " + order);
            } catch (IOException e) {
              e.printStackTrace();
            }
            orderHandlingService.handleOrder(order);
          });

          consumer.commitSync();
        }
      } catch (Exception e) {
        System.out.println(e);
      }

    }

  }
}
