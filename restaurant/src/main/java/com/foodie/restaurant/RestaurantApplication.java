package com.foodie.restaurant;

import com.foodie.restaurant.service.KafkaService;
import com.foodie.restaurant.service.orders.OrderHandlingService;
import com.foodie.restaurant.service.orders.OrderValidationService;
import com.foodie.restaurant.service.orders.OrdersKafkaConsumer;
import com.foodie.restaurant.service.restaurants.RestaurantsDao;
import com.foodie.restaurant.service.restaurants.RestaurantsService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestaurantApplication extends Application<RestaurantApplicationConfig> {

  @Override
  public void initialize(Bootstrap<RestaurantApplicationConfig> bootstrap) {

  }

  @Override
  public void run(RestaurantApplicationConfig configuration, Environment environment) throws Exception {
    var restaurantsDao = new RestaurantsDao();
    var kafkaService = new KafkaService(configuration.getKafka());
    var restaurantsService = new RestaurantsService(restaurantsDao);
    var orderValidationService = new OrderValidationService(restaurantsService, kafkaService);
    var orderHandlingService = new OrderHandlingService(kafkaService, orderValidationService);
    var ordersKafkaConsumer = new OrdersKafkaConsumer(configuration.getKafka(), orderHandlingService);

  }


  public static void main(String[] args) throws Exception {
    new RestaurantApplication().run(args);
  }
}
