package com.foodie.restaurant.service.restaurants;

import com.foodie.restaurant.api.Restaurant;
import com.foodie.restaurant.service.KafkaService;

import java.util.Optional;

public class RestaurantsService {

  private final RestaurantsDao restaurantsDao;


  public RestaurantsService(RestaurantsDao restaurantsDao) {
    this.restaurantsDao = restaurantsDao;
  }

  public Optional<Restaurant> findRestaurant(String uuid) {
    return restaurantsDao.findRestaurant(uuid);
  }
}
