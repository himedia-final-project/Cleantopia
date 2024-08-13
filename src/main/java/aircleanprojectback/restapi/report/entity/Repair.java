package aircleanprojectback.restapi.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_repair_report")
@AllArgsConstructor
@Getter
@ToString
//@Builder(toBuilder = true)
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

    @Column(name = "repair_report_status", length = 200)
    private String repairReportStatus;         // 보고서상태

    @Column(name = "repair_approve")
    private String repairApprove;           // 승인/반려

    @Column(name = "repair_content", length = 200)
    private String repairContent;                 // 내용

    @Column(name = "facility_count")
    private int facilityCount;                    // 시설물갯수

    @Column(name = "facility_type")
    private String facilityType;                  // 시설물 종류

    @Column(name = "facility_code")
    private int facilityCode;                     // 시설물코드

    @Column(name = "repair_photo")
    private String repairPhoto;                     // 고칠 사진

    @Column(name = "member_name")
    private String memberName;             // 지점장명

    @Column(name = "branch_name")
    private String branchName;              // 지점명

    @Column(name = "branch_code", length = 200)
    private  String branchCode;                   // 지점코드

    @Column(name = "branch_remark")
    private String branchRemark;                // 비고

    public Repair() {
    }

    public void setRepairReportCode(int repairReportCode) {
        this.repairReportCode = repairReportCode;
    }

    public void setRepairSubmissionDate(Date repairSubmissionDate) {
        this.repairSubmissionDate = repairSubmissionDate;
    }

    public void setRepairReportStatus(String repairReportStatus) {
        this.repairReportStatus = repairReportStatus;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public void setFacilityCount(int facilityCount) {
        this.facilityCount = facilityCount;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public void setFacilityCode(int facilityCode) {
        this.facilityCode = facilityCode;
    }

    public void setRepairPhoto(String repairPhoto) {
        this.repairPhoto = repairPhoto;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setRepairApprove(String repairApprove) {
        this.repairApprove = repairApprove;
    }

    public void setBranchRemark(String branchRemark) { this.branchRemark = branchRemark; }

    public Repair build() {
        return new Repair(
                repairReportCode,
                repairSubmissionDate,
                repairReportStatus,
                repairApprove,
                repairContent,
                facilityCount,
                facilityType,
                facilityCode,
                memberName,
                branchName,
                branchCode,
                repairPhoto,
                branchRemark
        );
    }


}