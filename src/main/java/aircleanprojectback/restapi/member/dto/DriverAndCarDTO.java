package aircleanprojectback.restapi.member.dto;

import aircleanprojectback.restapi.car.dto.CarDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverAndCarDTO {
    private String driverLicenseNumber;
    private String driverRegion;
    private String memberId;
    private String assignCar;
    private CarDTO carDTO;
}
