package aircleanprojectback.restapi.member.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembersAndOwnerDTO {

    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhoneNumber;
    private String memberRole;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date memberBirthDate;
    private String memberGender;
    private String memberAddress;
    private String memberStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date memberHireDate;
    private String branchOwnership;
    private String memberImage;

    private BranchOwnerDTO branchOwnerDTO;
}
