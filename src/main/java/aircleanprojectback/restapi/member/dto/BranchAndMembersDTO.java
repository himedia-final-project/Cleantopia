package aircleanprojectback.restapi.member.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchAndMembersDTO {

    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhoneNumber;
    private String memberRole;
    private Date memberBirthDate;
    private String memberGender;
    private String memberAddress;
    private String memberStatus;
    private Date memberHireDate;
    private String memberImage;
    private BranchDTO branchDTO;
}
