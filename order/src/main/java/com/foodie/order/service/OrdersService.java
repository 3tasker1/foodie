package com.foodie.order.service;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderStatus;
import com.foodie.order.dao.orders.OrdersDao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrdersService {

  private final OrdersDao ordersDao;
  private final KafkaService kafkaService;


  public OrdersService(OrdersDao ordersDao, KafkaService kafkaService) {
    this.ordersDao = ordersDao;
    this.kafkaService = kafkaService;
  }


  public List<Order> getOrders() {
    return ordersDao.getOrders();
  }

  public Optional<Order> findOrder(String uuid) {
    return ordersDao.findOrder(uuid);
  }

  public Order newOrder(String userUuid, String restaurantUuid) {
    var order = new Order(UUID.randomUUID().toString(), userUuid, restaurantUuid, 2000, OrderStatus.CREATED);
    ordersDao.saveOrder(order);
    kafkaService.publish(KafkaService.NEW_ORDERS_TOPIC, order);
    return order;
  }

  public void markOrderAsInvalid(Order order) {
    order.setOrderStatus(OrderStatus.INVALID);
    ordersDao.saveOrder(order);
  }

}
