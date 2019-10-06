package com.foodie.order;

import com.foodie.order.dao.orders.KafkaOrdersDao;
import com.foodie.order.dao.orders.LocalOrdersDao;
import com.foodie.order.resource.OrdersResource;
import com.foodie.order.service.KafkaService;
import com.foodie.order.service.OrdersService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class OrderApplication extends Application<OrderApplicationConfig> {
  @Override
  public void initialize(Bootstrap<OrderApplicationConfig> bootstrap) {

  }

  @Override
  public void run(OrderApplicationConfig configuration, Environment environment) throws Exception {

    var kafkaService = new KafkaService(configuration.getKafka());
    var ordersDao = new KafkaOrdersDao(kafkaService);
    var ordersService = new OrdersService(ordersDao);
    var ordersResource = new OrdersResource(ordersService);
    environment.jersey().register(ordersResource);

  }


  public static void main(String[] args) throws Exception {
    new OrderApplication().run(args);
  }
}
