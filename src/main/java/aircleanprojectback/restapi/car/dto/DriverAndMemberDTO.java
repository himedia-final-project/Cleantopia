package aircleanprojectback.restapi.car.dto;

import aircleanprojectback.restapi.member.dto.MemberDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverAndMemberDTO {

    private String driverLicenseNumber;
    private String assignCar;
    private String driverRegion;
    private MemberDTO memberDTO;
}
