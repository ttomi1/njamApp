package hr.tvz.zirdum.njamapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class RestaurantCommand {
    @NotNull(message = "Restaurant ID cannot be empty")
    private Long id;
    @NotBlank(message = "Restaurant name cannot be empty")
    private String name;
    @NotBlank(message = "Restaurant address cannot be empty")
    private String address;
    @NotBlank(message = "Restaurant phone cannot be empty")
    @Pattern(regexp = "\\+?[0-9\\- ]+", message = "Phone must be in correct format.")
    private String phone;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be in correct format")
    private String email;
    private Boolean currentlyOpen;
    @PositiveOrZero(message = "Average delivery time must be 0 or more")
    private Integer averageDeliveryTime;
    @PositiveOrZero(message = "Average client grade must be 0 or more")
    private Double averageClientGrade;
    @PositiveOrZero(message = "Maximum delivery count must be 0 or more")
    private Integer maximumDeliveryCount;
    @PositiveOrZero(message = "Michelin stars must be 0 or more")
    private Integer michelinStar;
    @NotBlank(message = "Description cannot be empty")
    private String shortDescription;
    @NotEmpty(message = "Work time must not be empty")
    private Map<String, String> workTime;
}
