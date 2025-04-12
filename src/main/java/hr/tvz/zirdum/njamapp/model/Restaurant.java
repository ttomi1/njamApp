package hr.tvz.zirdum.njamapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Boolean currentlyOpen;
    private Integer averageDeliveryTime;
    private Double averageClientGrade;
    private Integer maximumDeliveryCount;
    private Integer michelinStar;
    private String shortDescription;
    private Map<String, String> workTime;

}
