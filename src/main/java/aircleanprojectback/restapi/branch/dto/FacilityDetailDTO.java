package aircleanprojectback.restapi.branch.dto;

import aircleanprojectback.restapi.member.dto.BranchDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FacilityDetailDTO {

    private int facilityId;
    private BranchDTO branchDTO;
    private FacilityDTO facilityDTO;
    private String facilityStatus;
}
