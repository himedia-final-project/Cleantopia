package aircleanprojectback.restapi.report.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarMembersDTO {

    private String memberId;
    private String memberName;
    private String driverLicenseNumber;
    private String carNumber;
}
