package hr.tvz.zirdum.njamapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private Long id;
    @Getter
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
    public enum WorkTime {
        MONDAY("08:00-22:00"),
        TUESDAY("08:00-22:00"),
        WEDNESDAY("08:00-22:00"),
        THURSDAY("08:00-22:00"),
        FRIDAY("08:00-23:00"),
        SATURDAY("09:00-23:00"),
        SUNDAY("09:00-22:00");

        private String time;

        WorkTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }
    }

}
