package aircleanprojectback.restapi.member.dto;

import aircleanprojectback.restapi.car.dto.CarDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverDTO {

    private String driver_license_number;
    private String driverRegion;
    private MemberDTO memberDTO;
    private CarDTO carDTO;
}
