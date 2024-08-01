package aircleanprojectback.restapi.branchOrigin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date memberBirthDate;
    private String memberEmail;
    private String memberGender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date memberHireDate;
    private String memberImage;
    private String memberName;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberRole;
    private String memberStatus;
}
