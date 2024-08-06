package aircleanprojectback.restapi.report.dto;
import lombok.*;
import java.sql.Date;
//
//@Getter
//@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRepairDTO {
    private int vehicleReportCode;              // 본사보고서코드
    private String vehicleReportStatus;         // 보고서상태
    private String vehicleRepairApprove;        // 승인/반려
    private String beforeVehiclePhoto;          // 수리전사진
    private String afterVehiclePhoto;           // 수리후사진
    private String vehicleRemark;               // 비고
    private String vehicleFuelCost;             // 주유비
    private String vehicleRegularInspection;    // 정기점검
    private String vehicleVehicleRepairCost;    // 수리비
    private String vehicleMiscellaneous;        // 기타
    private Date vehicleSubmissionDate;         // 제출일
    private String driverLicenseNumber;         // 면허번호
    private int totalVehicleRepairCost;     // 총 수리비 금액
    private String memberName;
    private String carNumber;
    private String vehicleType;


    public int getVehicleReportCode() {
        return vehicleReportCode;
    }

    public void setVehicleReportCode(int vehicleReportCode) {
        this.vehicleReportCode = vehicleReportCode;
    }

    public String getVehicleReportStatus() {
        return vehicleReportStatus;
    }

    public void setVehicleReportStatus(String vehicleReportStatus) {
        this.vehicleReportStatus = vehicleReportStatus;
    }

    public String getBeforeVehiclePhoto() {
        return beforeVehiclePhoto;
    }

    public void setBeforeVehiclePhoto(String beforeVehiclePhoto) {
        this.beforeVehiclePhoto = beforeVehiclePhoto;
    }

    public String getAfterVehiclePhoto() {
        return afterVehiclePhoto;
    }

    public void setAfterVehiclePhoto(String afterVehiclePhoto) {
        this.afterVehiclePhoto = afterVehiclePhoto;
    }

    public String getVehicleRemark() {
        return vehicleRemark;
    }

    public void setVehicleRemark(String vehicleRemark) {
        this.vehicleRemark = vehicleRemark;
    }

    public String getVehicleFuelCost() {
        return vehicleFuelCost;
    }

    public void setVehicleFuelCost(String vehicleFuelCost) {
        this.vehicleFuelCost = vehicleFuelCost;
    }

    public String getVehicleRegularInspection() {
        return vehicleRegularInspection;
    }

    public void setVehicleRegularInspection(String vehicleRegularInspection) {
        this.vehicleRegularInspection = vehicleRegularInspection;
    }

    public String getVehicleVehicleRepairCost() {
        return vehicleVehicleRepairCost;
    }

    public void setVehicleVehicleRepairCost(String vehicleVehicleRepairCost) {
        this.vehicleVehicleRepairCost = vehicleVehicleRepairCost;
    }

    public String getVehicleMiscellaneous() {
        return vehicleMiscellaneous;
    }

    public void setVehicleMiscellaneous(String vehicleMiscellaneous) {
        this.vehicleMiscellaneous = vehicleMiscellaneous;
    }

    public Date getVehicleSubmissionDate() {
        return vehicleSubmissionDate;
    }

    public void setVehicleSubmissionDate(Date vehicleSubmissionDate) {
        this.vehicleSubmissionDate = vehicleSubmissionDate;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public int getTotalVehicleRepairCost() {
        return totalVehicleRepairCost;
    }

    public void setTotalVehicleRepairCost(int totalVehicleRepairCost) {
        this.totalVehicleRepairCost = totalVehicleRepairCost;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleRepairApprove() {
        return vehicleRepairApprove;
    }

    public void setVehicleRepairApprove(String vehicleRepairApprove) {
        this.vehicleRepairApprove = vehicleRepairApprove;
    }
}
