package aircleanprojectback.restapi.branchOrigin.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembersPageDTO {

    private String memberId;
    private String branchOwnerShip;
    private String memberAddress;
    private Date memberBirthDate;
    private String memberEmail;
    private String memberGender;
    private Date memberHireDate;
    private String memberImage;
    private String memberName;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberRole;
    private String memberStatus;
}
