package hr.tvz.zirdum.njamapp.service;

import hr.tvz.zirdum.njamapp.dto.RestaurantCommand;
import hr.tvz.zirdum.njamapp.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
    RestaurantDTO findRestaurantByID(Long id);
    List<RestaurantDTO> findClosest(String address);
    List<RestaurantDTO> findBest(Double grade);
    RestaurantCommand saveOrUpdate(RestaurantCommand restaurantCommand);
    void deleteRestaurant(Long id);
    RestaurantDTO findRestaurantByName(String name);
}
