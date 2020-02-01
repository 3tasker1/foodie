package com.foodie.restaurant.service.orders;

import com.foodie.restaurant.api.Order;
import com.foodie.restaurant.service.KafkaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderHandlingService {

  private final KafkaService kafkaService;
  private List<OrderHandler> registeredOrderHandlers = new ArrayList<>();

  public OrderHandlingService(KafkaService kafkaService, OrderHandler... orderHandlers) {
    this.kafkaService = kafkaService;
    registeredOrderHandlers.addAll(Arrays.asList(orderHandlers));
  }

  void handleOrder(Order order) {
    var allRespondedOkay = true;
    for(var handler : registeredOrderHandlers) {
      var response = handler.handle(order);
      if(response.equals(OrderHandlerResponse.NOT_OKAY)) {
        allRespondedOkay = false;
        break;
      }
    }

    if(!allRespondedOkay) {
      //kafkaService.publish();
    }
  }

  public enum OrderHandlerResponse {
    OKAY,
    NOT_OKAY;
  }
}
