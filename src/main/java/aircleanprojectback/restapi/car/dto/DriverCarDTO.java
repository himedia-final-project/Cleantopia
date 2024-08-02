package aircleanprojectback.restapi.car.dto;

import aircleanprojectback.restapi.member.dto.MemberDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverCarDTO {

    private String driverLicenseNumber; // 면허번호
    private String driverRegion; // 지역
    private MemberDTO memberDTO; // 사원ID
}
