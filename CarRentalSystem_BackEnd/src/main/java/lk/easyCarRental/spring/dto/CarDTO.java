package lk.easyCarRental.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarDTO {
    private String carId;
    private String carBrand;
    private String type;
    private int numberOfPassengers;
    private String transmissionType;
    private int mileage;
    private String freeMileage;
    private double dailyRentPrice;
    private double monthlyRentPrice;
    private double ldwFee;
    private double extraKMPrice;
    private String fuelType;
    private List<CarDetailDTO> vehicleDetailList;
}
