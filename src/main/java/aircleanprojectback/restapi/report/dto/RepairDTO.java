package aircleanprojectback.restapi.report.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepairDTO {
    // 지점 시설물 수리보고서

    private int repairReportCode;                 // 본사수리보고서코드
    private Date repairSubmissionDate;            // 보고서 제출일
    private String repairReportStatus;            // 보고서상태
    private String repairContent;                 // 내용
    private int facilityCount;                    // 시설물갯수
    private int facilityCode;                     // 시설물코드
    private String memberName;                    // 지점장
    private String branchName;                    // 지점명
    private  String branchCode;                   // 지점코드
}
