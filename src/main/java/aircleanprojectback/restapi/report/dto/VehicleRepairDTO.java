package aircleanprojectback.restapi.report.dto;

import lombok.*;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
    private int vehicleFuelCost;                // 주유비
    private int vehicleRegularInspection;       // 정기점검
    private int vehicleVehicleRepairCost;       // 수리비
    private int vehicleMiscellaneous;           // 기타
    // 시설물수리비
    private Date vehicleSubmissionDate;         // 제출일
    private Date vehicleReportDate;             // 영수증 날짜
    private String driverLicenseNumber;         // 면허번호
    private int totalVehicleRepairCost;         // 총 수리비 금액
    private String memberName;                  // 차량기사 이름
    private String carNumber;                   // 차량번호
    private String vehicleType;                 // 차량비용 종류


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

    public int getVehicleFuelCost() {
        return vehicleFuelCost;
    }

    public void setVehicleFuelCost(int vehicleFuelCost) {
        this.vehicleFuelCost = vehicleFuelCost;
    }

    public int getVehicleRegularInspection() {
        return vehicleRegularInspection;
    }

    public void setVehicleRegularInspection(int vehicleRegularInspection) {
        this.vehicleRegularInspection = vehicleRegularInspection;
    }

    public int getVehicleVehicleRepairCost() {
        return vehicleVehicleRepairCost;
    }

    public void setVehicleVehicleRepairCost(int vehicleVehicleRepairCost) {
        this.vehicleVehicleRepairCost = vehicleVehicleRepairCost;
    }

    public int getVehicleMiscellaneous() {
        return vehicleMiscellaneous;
    }

    public void setVehicleMiscellaneous(int vehicleMiscellaneous) {
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

    public Date getVehicleReportDate() {
        return vehicleReportDate;
    }
    public void setVehicleReportDate(Date vehicleReportDate) {
        this.vehicleReportDate = vehicleReportDate;
    }
}