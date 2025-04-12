package hr.tvz.zirdum.njamapp.service;

import hr.tvz.zirdum.njamapp.dto.RestaurantCommand;
import hr.tvz.zirdum.njamapp.dto.RestaurantDTO;
import hr.tvz.zirdum.njamapp.model.Restaurant;
import hr.tvz.zirdum.njamapp.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public RestaurantDTO findRestaurantByID(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findRestaurantById(id);
        return restaurant.map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with ID " + id + " not found."));
    }

    @Override
    public RestaurantDTO findRestaurantByName(String name) {
        return restaurantRepository.findAll().stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with name '" + name + "' not found."));
    }

    @Override
    public List<RestaurantDTO> findClosest(String address) {
        return findAll().stream()
                .filter(r -> r.getAddress().toLowerCase().contains(address.toLowerCase()))
                .toList();
    }

    @Override
    public List<RestaurantDTO> findBest(Double grade) {
        return restaurantRepository.findAll().stream()
                .filter(r -> r.getAverageClientGrade() >= grade)
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public RestaurantCommand saveOrUpdate(RestaurantCommand restaurantCommand) {
        Optional<Restaurant> savedRestaurant = Optional.ofNullable(restaurantRepository.saveOrUpdate(restaurantCommand));

        if (!savedRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A restaurant with this name already exists");
        }
        return restaurantCommand;
    }

    @Override
    public void deleteRestaurant(Long id) {
        boolean deleted = restaurantRepository.deleteById(id);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant with ID " + id + " not found.");
        }
    }

    @Override
    public List<Restaurant> findAllModel() {
        return restaurantRepository.findAll()
                .stream()
                .toList();
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        int activeOrders = getRandomOrderCount();
        return new RestaurantDTO(restaurant, activeOrders);
    }



    private int getRandomOrderCount() {
        return new Random().nextInt(10, 100);
    }
}
