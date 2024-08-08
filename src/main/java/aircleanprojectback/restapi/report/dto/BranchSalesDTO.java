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
    private int branchReportCode;           // 매출보고서코드
    private Date branchSubmissionDate;      // 제출일
    private int officeSales;             // 오프라인매출
    private String branchReportStatus;      // 보고서상태
    private String branchReportApprove;     // 승인/반려
    private int detergent;               // 세제
    private int fabricSoftener;          // 섬유유연제
    private int bleach;                  // 표백제
    private int stainRemover;            // 얼룩제거제
    private int washerCleaner;           // 세탁조 클리너
    private int dryerSheet;              // 건조기시트
    private int totalBranchSalesCost;    // 총매출 금액
    private String memberName;              // 지점장
    private String branchName;              // 지점명
    private String branchCode;              // 지점코드
    private String memberId;
    private String branchSalesRemark;       // 비고




}