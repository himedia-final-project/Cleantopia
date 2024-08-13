package aircleanprojectback.restapi.report.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

//@Getter
//@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepairDTO {
    // 지점 시설물 수리보고서

    private int repairReportCode;                 // 본사수리보고서코드
    private Date repairSubmissionDate;            // 보고서 제출일
    private String repairReportStatus;            // 보고서상태
    private String repairApprove;                 // 승인/반려
    private String repairContent;                 // 내용
    private int facilityCount;                    // 시설물갯수
    private int facilityCode;                     // 시설물코드
    private String memberName;                    // 지점장
    private String branchName;                    // 지점명
    private  String branchCode;                   // 지점코드
    private String facilityType;                  // 시설물 종류
    private String repairPhoto;                   // 첨부사진
    private String branchRemark;                  //비고

    public int getRepairReportCode() {
        return repairReportCode;
    }

    public void setRepairReportCode(int repairReportCode) {
        this.repairReportCode = repairReportCode;
    }

    public Date getRepairSubmissionDate() {
        return repairSubmissionDate;
    }

    public void setRepairSubmissionDate(Date repairSubmissionDate) {
        this.repairSubmissionDate = repairSubmissionDate;
    }

    public String getRepairReportStatus() {
        return repairReportStatus;
    }

    public void setRepairReportStatus(String repairReportStatus) {
        this.repairReportStatus = repairReportStatus;
    }

    public String getRepairContent() {
        return repairContent;
    }

    public void setRepairContent(String repairContent) {
        this.repairContent = repairContent;
    }

    public int getFacilityCount() {
        return facilityCount;
    }

    public void setFacilityCount(int facilityCount) {
        this.facilityCount = facilityCount;
    }

    public int getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(int facilityCode) {
        this.facilityCode = facilityCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getRepairPhoto() {
        return repairPhoto;
    }

    public void setRepairPhoto(String repairPhoto) {
        this.repairPhoto = repairPhoto;
    }

    public String getRepairApprove() {
        return repairApprove;
    }

    public void setRepairApprove(String repairApprove) {
        this.repairApprove = repairApprove;
    }

    public String getBranchRemark() { return branchRemark; }

    public void setBranchRemark(String branchRemark) { this.branchRemark = branchRemark; }

}