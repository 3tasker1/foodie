package com.foodie.restaurant;

import com.foodie.restaurant.service.OrdersKafkaConsumer;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestaurantApplication extends Application<RestaurantApplicationConfig> {

  @Override
  public void initialize(Bootstrap<RestaurantApplicationConfig> bootstrap) {

  }

  @Override
  public void run(RestaurantApplicationConfig configuration, Environment environment) throws Exception {
    var ordersKafkaConsumer = new OrdersKafkaConsumer(configuration.getKafka());
    var ordersKafkaConsumerThread = new Thread(ordersKafkaConsumer);
    ordersKafkaConsumerThread.start();

  }


  public static void main(String[] args) throws Exception {
    new RestaurantApplication().run(args);
  }
}
