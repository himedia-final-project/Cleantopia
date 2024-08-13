package aircleanprojectback.restapi.mainpage.model.entity;

import aircleanprojectback.restapi.member.entity.Branch;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_branch_sales_report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BranchSalesAndBranch {

    @Id
    @Column(name = "branch_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchReportCode;        // 매출보고서코드

    @Column(name = "branch_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date branchSubmissionDate;      // 제출일

    @Column(name = "offline_sales", nullable = true)
    private long officeSales;                // 오프라인매출

    @Column(name = "branch_report_status", length = 100)
    private String branchReportStatus ;      // 보고서상태

    @Column(name = "branch_report_approve")
    private String branchReportApprove;      // 승인/ 반려

    @Column(name = "detergent", length = 200, nullable = true)
    private long detergent;               // 세제

    @Column(name = "fabric_softener", length = 200, nullable = true)
    private long fabricSoftener;          // 섬유유연제

    @Column(name = "bleach", length = 200, nullable = true)
    private long bleach;                  // 표백제

    @Column(name = "stain_remover", length = 200, nullable = true)
    private long stainRemover;            // 얼룩제거제

    @Column(name = "washer_cleaner", length = 200, nullable = true)
    private long washerCleaner;           // 세탁조 클리너

    @Column(name = "dryer_sheet", length = 200, nullable = true)
    private long dryerSheet;              // 건조기시트

    @Column(name = "total_branch_sales_cost")
    private long totalBranchSalesCost;      // 총지출금액

    @Column(name = "member_name")
    private String memberName;;             // 지점장명

    @Column(name = "branch_name")
    private String branchName;              // 지점명


    @ManyToOne
    @JoinColumn(name = "branch_code")
    private Branch branch;

    @Column(name = "member_id", nullable = true)
    private String memberId;

    @Column(name = "branch_sales_remark")
    private String branchSalesRemark;       // 비고
}
