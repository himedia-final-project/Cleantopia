package aircleanprojectback.restapi.report.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor      // 기본생성자
@AllArgsConstructor     // 생성자
public class BranchSalesDTO {
    // 지점 매출 보고서
    private int branchReportCode;        // 매출보고서코드
    private Date branchSubmissionDate;      // 제출일
    private String officeSales;                // 오프라인매출
    private String branchReportStatus;      // 보고서상태
    private String detergent;               // 세제
    private String fabricSoftener;          // 섬유유연제
    private String bleach;                  // 표백제
    private String stainRemover;            // 얼룩제거제
    private String washerCleaner;           // 세탁조 클리너
    private String dryerSheet;              // 건조기시트
    private String totalBranchSalesCost;       // 총매출 금액
    private String memberName;              // 지점장
    private String branchName;              // 지점명
    private String branchCode;              // 지점코드
    private String memberId;




}
