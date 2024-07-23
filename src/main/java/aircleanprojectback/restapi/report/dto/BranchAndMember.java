package aircleanprojectback.restapi.report.dto;
import aircleanprojectback.restapi.member.entity.Members;
import lombok.*;

import java.sql.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchAndMember {

    private int branchReportCode;        // 매출보고서코드
    private Date branchSubmissionDate;      // 제출일
    private int officeSales;                // 오프라인매출
    private String branchReportStatus;      // 보고서상태
    private String detergent;               // 세제
    private String fabricSoftener;          // 섬유유연제
    private String bleach;                  // 표백제
    private String stainRemover;            // 얼룩제거제
    private String washerCleaner;           // 세탁조 클리너
    private String dryerSheet;              // 건조기시트
    private String branchCode;              // 지점코드
    private Members memberId;
}
