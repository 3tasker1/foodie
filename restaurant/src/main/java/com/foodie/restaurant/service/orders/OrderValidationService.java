package com.foodie.restaurant.service.orders;

import com.foodie.restaurant.api.Order;
import com.foodie.restaurant.service.KafkaService;
import com.foodie.restaurant.service.restaurants.RestaurantsService;

import static com.foodie.restaurant.service.KafkaService.INVALID_ORDER_TOPIC;

public class OrderValidationService implements OrderHandler{

  private final RestaurantsService restaurantsService;
  private final KafkaService kafkaService;


  public OrderValidationService(RestaurantsService restaurantsService, KafkaService kafkaService) {

    this.restaurantsService = restaurantsService;
    this.kafkaService = kafkaService;
  }

  @Override
  public OrderHandlingService.OrderHandlerResponse handle(Order order) {
    if(restaurantsService.findRestaurant(order.getRestaurantUuid()).isEmpty()) {
      kafkaService.publish(INVALID_ORDER_TOPIC, order.getUuid(), order);
      return OrderHandlingService.OrderHandlerResponse.NOT_OKAY;
    }
    return OrderHandlingService.OrderHandlerResponse.OKAY;
  }
}
