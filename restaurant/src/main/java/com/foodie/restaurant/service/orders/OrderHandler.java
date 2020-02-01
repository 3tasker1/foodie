package com.foodie.restaurant.service.orders;

import com.foodie.restaurant.api.Order;

public interface OrderHandler {

  OrderHandlingService.OrderHandlerResponse handle(Order order);

}
