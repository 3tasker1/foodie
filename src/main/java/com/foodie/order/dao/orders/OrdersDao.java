package com.foodie.order.dao.orders;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderRequest;

import java.util.List;

public interface OrdersDao {

  Order saveOrder(OrderRequest orderRequest);

  List<Order> getOrders();

}
