package aircleanprojectback.restapi.member.dto;

import lombok.*;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembersAndBranchDTO {

    private String branchCode;
    private Date branchOpenDate;
    private String branchAddress;
    private String branchImage;
    private String branchName;
    private String branchPhone;
    private String branchRegion;
    private MemberDTO memberDTO;
}
