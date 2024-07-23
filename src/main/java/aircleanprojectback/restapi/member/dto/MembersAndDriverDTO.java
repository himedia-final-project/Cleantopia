package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembersAndDriverDTO {

    private String driverLicenseNumber;
    private String driverRegion;
    private MemberDTO memberDTO;

}
