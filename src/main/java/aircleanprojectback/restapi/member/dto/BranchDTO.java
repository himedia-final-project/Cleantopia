package aircleanprojectback.restapi.member.dto;



import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchDTO {

    private String branchCode;
    private String branchRegion;
    private String branchName;
    private String branchPhone;
    private String branchAddress;
    private String branchImage;
    private Date branchOpenDate;
    private String memberId;
    private String ownerStatus;
}
