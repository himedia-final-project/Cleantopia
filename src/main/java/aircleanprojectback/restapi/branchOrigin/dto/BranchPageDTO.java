package aircleanprojectback.restapi.branchOrigin.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchPageDTO {
    private String branchCode;
    private String branchAddress;
    private String branchImage;
    private String branchName;
    private Date branchOpenDate;
    private String branchPhone;
    private String branchRegion;
    private String ownerStatus;
    private List<BranchOwnerPageDTO> branchOwnerPageDTOS;
}
