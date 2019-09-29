package com.foodie.order.dao.orders;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderRequest;
import com.foodie.order.service.KafkaService;

import java.util.List;
import java.util.UUID;

public class KafkaOrdersDao implements OrdersDao {

  private final KafkaService kafkaService;

  private final static String NEW_ORDER_TOPIC = "NEW-ORDER";

  public KafkaOrdersDao(KafkaService kafkaService) {
    this.kafkaService = kafkaService;
  }

  @Override
  public Order saveOrder(OrderRequest orderRequest) {
    var newOrder = Order.fromRequest(orderRequest, UUID.randomUUID().toString());
    kafkaService.publish(NEW_ORDER_TOPIC, newOrder.getUuid(), newOrder.toString());
    return newOrder;
  }

  @Override
  public List<Order> getOrders() {
    return null;
  }
}
