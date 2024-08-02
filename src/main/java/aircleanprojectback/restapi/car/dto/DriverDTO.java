package aircleanprojectback.restapi.car.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@ToString
public class DriverDTO {


    private String driverLicenseNumber;


    private String assignCar;


    private String driverRegion;


    private String memberId;

}
