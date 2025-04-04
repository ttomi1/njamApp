package hr.tvz.zirdum.njamapp.dto;

import hr.tvz.zirdum.njamapp.model.Restaurant;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private Boolean currentlyOpen;
    private Double overloadPercentage;

    public RestaurantDTO(Restaurant restaurant, int activeOrdersCount) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.currentlyOpen = restaurant.getCurrentlyOpen();
        this.overloadPercentage = (double) activeOrdersCount / restaurant.getMaximumDeliveryCount() * 100;
    }


    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String address, Boolean currentlyOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.currentlyOpen = currentlyOpen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getCurrentlyOpen() {
        return currentlyOpen;
    }

    public void setCurrentlyOpen(Boolean currentlyOpen) {
        this.currentlyOpen = currentlyOpen;
    }

    public Double getOverloadPercentage() {
        return overloadPercentage;
    }

    public void setOverloadPercentage(Double overloadPercentage) {
        this.overloadPercentage = overloadPercentage;
    }
}
