package hr.tvz.zirdum.njamapp.repository;

import hr.tvz.zirdum.njamapp.dto.RestaurantCommand;
import hr.tvz.zirdum.njamapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    List<Restaurant> findAll();
    Optional<Restaurant> findRestaurantById(Long id);
    Restaurant saveOrUpdate(RestaurantCommand restaurant);
    boolean deleteById(Long id);
}
