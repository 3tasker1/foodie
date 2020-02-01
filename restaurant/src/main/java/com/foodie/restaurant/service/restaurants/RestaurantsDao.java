package com.foodie.restaurant.service.restaurants;

import com.foodie.restaurant.api.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantsDao {

  private List<Restaurant> restaurantList = new ArrayList<>();

  public RestaurantsDao() {
    restaurantList.add(new Restaurant("123", "Chris' Rib Joint"));
    restaurantList.add(new Restaurant("456", "Chris' Burger Joint"));
  }

  Optional<Restaurant> findRestaurant(String uuid) {
    return restaurantList.stream()
      .filter(r -> r.getUuid().equals(uuid))
      .findFirst();
  }

}
