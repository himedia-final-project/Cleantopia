package aircleanprojectback.restapi.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_branch_sales_report")
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class BranchSales {

    @Id
    @Column(name = "branch_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchReportCode;        // 매출보고서코드

    @Column(name = "branch_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date branchSubmissionDate;      // 제출일

    @Column(name = "offline_sales")
    private int officeSales;                // 오프라인매출

    @Column(name = "branch_report_status", length = 100, nullable = false)
    private String branchReportStatus = "접수";      // 보고서상태

    @Column(name = "detergent", length = 200, nullable = false)
    private String detergent;               // 세제

    @Column(name = "fabric_softener", length = 200, nullable = false)
    private String fabricSoftener;          // 섬유유연제

    @Column(name = "bleach", length = 200, nullable = false)
    private String bleach;                  // 표백제

    @Column(name = "stain_remover", length = 200, nullable = false)
    private String stainRemover;            // 얼룩제거제

    @Column(name = "washer_cleaner", length = 200, nullable = false)
    private String washerCleaner;           // 세탁조 클리너

    @Column(name = "dryer_sheet", length = 200, nullable = false)
    private String dryerSheet;              // 건조기시트

    @Column(name = "branch_code", length = 200, nullable = false)
    private String branchCode;              // 지점코드

    @Column(name = "member_id")
    private String memberId;

    public BranchSales() {

        this.branchReportStatus = "접수";
    }

   public  BranchSales branchReportCode(int branchReportCode) {
        this.branchReportCode = branchReportCode;
        return this;
   }

   public  BranchSales branchSubmissionDate(Date branchSubmissionDate) {
        this.branchSubmissionDate = branchSubmissionDate;
        return this;
   }

   public  BranchSales officeSales(int officeSales) {
        this.officeSales = officeSales;
        return this;
   }

   public  BranchSales branchReportStatus(String branchReportStatus) {
        this.branchReportStatus = branchReportStatus;
        return this;
   }

   public  BranchSales detergent(String detergent) {
        this.detergent = detergent;
        return this;
   }


   public  BranchSales fabricSoftener(String fabricSoftener) {
        this.fabricSoftener = fabricSoftener;
        return this;
   }

   public  BranchSales bleach(String bleach) {
        this.bleach = bleach;
        return this;
   }

   public  BranchSales stainRemover(String stainRemover) {
        this.stainRemover = stainRemover;
        return this;
   }

   public  BranchSales washerCleaner(String washerCleaner) {
        this.washerCleaner = washerCleaner;
        return this;
   }

   public  BranchSales dryerSheet(String dryerSheet) {
        this.dryerSheet = dryerSheet;
        return this;
   }

   public  BranchSales branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
   }

    public  BranchSales memberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

   public BranchSales build() {
        return new BranchSales(branchReportCode, branchSubmissionDate, officeSales, branchReportStatus, detergent, fabricSoftener, bleach, stainRemover, washerCleaner, dryerSheet, branchCode, memberId);
   }

    public void setBranchReportStatus(String branchReportStatus) {
        this.branchReportStatus = branchReportStatus;
    }
}
