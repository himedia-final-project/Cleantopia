package aircleanprojectback.restapi.mainpage.model.dto;

import aircleanprojectback.restapi.member.dto.BranchDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchSalesAndBranchDTO {

    // 지점 매출 보고서
    private int branchReportCode;           // 매출보고서코드
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date branchSubmissionDate;      // 제출일
    private long officeSales;             // 오프라인매출
    private String branchReportStatus;      // 보고서상태
    private String branchReportApprove;     // 승인/반려
    private long detergent;               // 세제
    private long fabricSoftener;          // 섬유유연제
    private long bleach;                  // 표백제
    private long stainRemover;            // 얼룩제거제
    private long washerCleaner;           // 세탁조 클리너
    private long dryerSheet;              // 건조기시트
    private long totalBranchSalesCost;    // 총매출 금액
    private String memberName;              // 지점장
    private String branchName;              // 지점명
    private BranchDTO branch;              // 지점코드
    private String memberId;
    private String branchSalesRemark;
}
