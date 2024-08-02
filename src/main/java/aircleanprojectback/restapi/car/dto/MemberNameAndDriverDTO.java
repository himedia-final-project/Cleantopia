package aircleanprojectback.restapi.car.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberNameAndDriverDTO {

    private String memberId;

    private String memberName;


    private String driverLicenseNumber;


    private String driverRegion;


    private String assignCar;

}
