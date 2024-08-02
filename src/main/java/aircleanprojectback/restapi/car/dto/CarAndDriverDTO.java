package aircleanprojectback.restapi.car.dto;

import lombok.*;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarAndDriverDTO {

    private String carNumber;
    private String branchRegion;
    private String carAssignedStatus;
    private Date carDate;
    private String carEtc;
    private String carPhoto;
    private DriverAndMemberDTO driverAndMemberDTO;
}
