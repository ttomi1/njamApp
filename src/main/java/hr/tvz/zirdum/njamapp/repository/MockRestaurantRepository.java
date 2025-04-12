package hr.tvz.zirdum.njamapp.repository;

import hr.tvz.zirdum.njamapp.dto.RestaurantCommand;
import hr.tvz.zirdum.njamapp.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MockRestaurantRepository implements RestaurantRepository {

    private final List<Restaurant> restaurantList = new ArrayList<>();

    public MockRestaurantRepository() {

        restaurantList.add(new Restaurant(
                1L,
                "Pizzeria Napoli",
                "Trg Bana Jelačića 1, Zagreb",
                "+38512345678",
                "napoli@pizzeria.hr",
                true,
                30,
                4.5,
                50,
                5,
                "Pizza restoran",
                new HashMap<>() {{
                    put("MONDAY", "08:00-22:00");
                    put("TUESDAY", "08:00-22:00");
                    put("WEDNESDAY", "08:00-22:00");
                    put("THURSDAY", "08:00-22:00");
                    put("FRIDAY", "08:00-23:00");
                    put("SATURDAY", "09:00-23:00");
                    put("SUNDAY", "09:00-22:00");
                }}
        ));
        restaurantList.add(new Restaurant(
                2L,
                "Bistro Dobar Tek",
                "Ilica 15, Zagreb",
                "+38598765432",
                "dobartek@bistro.hr",
                false,
                25,
                4.2,
                40,
                4,
                "Bistro restoran",
                new HashMap<>() {{
                    put("MONDAY", "09:00-21:00");
                    put("TUESDAY", "09:00-21:00");
                    put("WEDNESDAY", "09:00-21:00");
                    put("THURSDAY", "09:00-21:00");
                    put("FRIDAY", "09:00-22:00");
                    put("SATURDAY", "10:00-22:00");
                    put("SUNDAY", "Zatvoreno");
                }}
        ));
    }

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurantList);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long id) {
        return restaurantList.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    @Override
    public Restaurant saveOrUpdate(RestaurantCommand restaurant) {

        if (existsByNameAndAddress(restaurant.getName(), restaurant.getAddress())) {
            return null;
        }

        Restaurant restaurant1 = new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getPhone(),
                                                restaurant.getEmail(), restaurant.getCurrentlyOpen(), restaurant.getAverageDeliveryTime(),
                                                restaurant.getAverageClientGrade(), restaurant.getMaximumDeliveryCount(),
                                                restaurant.getMichelinStar(), restaurant.getShortDescription(), restaurant.getWorkTime());

        restaurantList.add(restaurant1);
        return restaurant1;
    }


    public boolean existsByNameAndAddress(String name, String address) {
        return restaurantList.stream()
                .anyMatch(r -> r.getName().equalsIgnoreCase(name) && r.getAddress().equalsIgnoreCase(address));
    }

    @Override
    public boolean deleteById(Long id) {
        return restaurantList.removeIf(r -> r.getId().equals(id));
    }
}
