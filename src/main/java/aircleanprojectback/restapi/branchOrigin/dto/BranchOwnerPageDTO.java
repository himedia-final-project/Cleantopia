package aircleanprojectback.restapi.branchOrigin.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchOwnerPageDTO {

    private int ownerCode;
    private String branchCode;
    private MembersPageDTO membersPageDTO;

}
