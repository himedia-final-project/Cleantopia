package aircleanprojectback.restapi.branchOrigin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date branchOpenDate;
    private String branchPhone;
    private String branchRegion;
    private String ownerStatus;
    private List<BranchOwnerPageDTO> branchOwnerPageDTOS;
}
