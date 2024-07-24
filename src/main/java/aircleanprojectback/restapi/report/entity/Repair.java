package aircleanprojectback.restapi.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_repair_report")
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class Repair {

    @Id
    @Column(name = "repair_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int repairReportCode;              // 본사수리보고서코드

    @Column(name = "repair_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date repairSubmissionDate;            // 보고서 제출일

    @Column(name = "repair_report_status", length = 200, nullable = false)
    private String repairReportStatus = "접수";            // 보고서상태

    @Column(name = "repair_content", length = 200, nullable = false)
    private String repairContent;                 // 내용

    @Column(name = "facility_count")
    private int facilityCount;                    // 시설물갯수

    @Column(name = "facility_code")
    private int facilityCode;                     // 시설물코드

    @Column(name = "branch_code", length = 200, nullable = false)
    private  String branchCode;                   // 지점코드

    public Repair() {

        this.repairReportStatus = "접수";
    }


    public Repair repairReportCode(int repairReportCode) {
        this.repairReportCode = repairReportCode;
        return this;
    }

    public Repair repairSubmissionDate(Date repairSubmissionDate) {
        this.repairSubmissionDate = repairSubmissionDate;
        return this;
    }

    public Repair repairReportStatus(String repairReportStatus) {
        this.repairReportStatus = repairReportStatus;
        return this;
    }

    public Repair repairContent(String repairContent) {
        this.repairContent = repairContent;
        return this;
    }

    public Repair facilityCount(int facilityCount) {
        this.facilityCount = facilityCount;
        return this;
    }

    public Repair facilityCode(int facilityCode) {
        this.facilityCode = facilityCode;
        return this;
    }

    public Repair branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public  Repair build() {
        return new Repair(repairReportCode, repairSubmissionDate, repairReportStatus, repairContent, facilityCount, facilityCode, branchCode);
    }
}
