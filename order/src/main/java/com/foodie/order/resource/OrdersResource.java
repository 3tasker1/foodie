package com.foodie.order.resource;

import com.foodie.order.api.Order;
import com.foodie.order.api.OrderRequestForm;
import com.foodie.order.service.OrdersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

  private final OrdersService ordersService;

  public OrdersResource(OrdersService ordersService) {
    this.ordersService = ordersService;
  }


  @GET
  public List<Order> getOrders() {
    return ordersService.getOrders();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void newOrder(OrderRequestForm orderRequestForm) {
    ordersService.newOrder(orderRequestForm.getUserUuid(), orderRequestForm.getRestaurantUuid());
  }

}
