package hr.tvz.zirdum.njamapp.controller;

import hr.tvz.zirdum.njamapp.dto.RestaurantCommand;
import hr.tvz.zirdum.njamapp.dto.RestaurantDTO;
import hr.tvz.zirdum.njamapp.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/restaurant")
public class RestController {

    private final RestaurantService restaurantService;

    public RestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantByID(id));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RestaurantDTO> getRestaurantByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(restaurantService.findRestaurantByName(name));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/restaurantItem")
    public ResponseEntity<RestaurantCommand> saveNewRestaurantItem(@Valid @RequestBody RestaurantCommand restaurantCommand) {
        try {
            Optional<RestaurantCommand> savedRestaurantCommand = Optional.ofNullable(restaurantService.saveOrUpdate(restaurantCommand));
            return ResponseEntity.status(201).body(savedRestaurantCommand.get());
        } catch (ResponseStatusException e)
        {
            return ResponseEntity.status(409).body(restaurantCommand);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        try {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Restaurant deleted successfully.");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getReason());
        }
    }
}
