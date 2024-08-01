package aircleanprojectback.restapi.member.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date branchOpenDate;
    private String ownerStatus;
}
