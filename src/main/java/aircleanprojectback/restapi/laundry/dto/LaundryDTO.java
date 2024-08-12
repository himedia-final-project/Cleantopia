package aircleanprojectback.restapi.laundry.dto;


import jakarta.persistence.Column;
import lombok.*;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundryDTO {

    private int laundryCode;
    private String laundryCollectionStatus;
    private String laundryArriveStatus;
    private String laundryCustomerName;
    private int laundryWeight;
    private String laundryFabricType;
    private Date laundryCustomerRegistDate;
    private String laundryWashingInstructionStatus;
    private Date laundryApprovalDate;
    private String laundryDryCleaningStatus;
    private int laundryDirtyLevel;
    private String branchCode;
    private String driverLicenseNumber;
    private Date laundryDriverPickupDate;
    private String laundryCompleted;
    private Date laundryCompletedDate;
    private String deliveryDriver;
    private String pickupDriver;
    private String bringCustomerStatus;
    private String cleaningStatus;
    private String dryStatus;
    private String allComplete;
    private Date allCompleteDate;

//    public LaundryDTO() {
//    }
//
//    public LaundryDTO(int laundryCode, String laundryCollectionStatus, String laundryArriveStatus, String laundryCustomerName, int laundryWeight, String laundryFabricType, Date laundryCustomerRegistDate, String laundryWashingInstructionStatus, Date laundryApprovalDate, String laundryDryCleaningStatus, int laundryDirtyLevel, String branchCode, String driverLicenseNumber, Date laundryDriverPickupDate, String laundryCompleted, Date laundryCompletedDate) {
//        this.laundryCode = laundryCode;
//        this.laundryCollectionStatus = laundryCollectionStatus;
//        this.laundryArriveStatus = laundryArriveStatus;
//        this.laundryCustomerName = laundryCustomerName;
//        this.laundryWeight = laundryWeight;
//        this.laundryFabricType = laundryFabricType;
//        this.laundryCustomerRegistDate = laundryCustomerRegistDate;
//        this.laundryWashingInstructionStatus = laundryWashingInstructionStatus;
//        this.laundryApprovalDate = laundryApprovalDate;
//        this.laundryDryCleaningStatus = laundryDryCleaningStatus;
//        this.laundryDirtyLevel = laundryDirtyLevel;
//        this.branchCode = branchCode;
//        this.driverLicenseNumber = driverLicenseNumber;
//        this.laundryDriverPickupDate = laundryDriverPickupDate;
//        this.laundryCompleted = laundryCompleted;
//        this.laundryCompletedDate = laundryCompletedDate;
//    }
//
//    public int getLaundryCode() {
//        return laundryCode;
//    }
//
//    public void setLaundryCode(int laundryCode) {
//        this.laundryCode = laundryCode;
//    }
//
//    public String getLaundryCollectionStatus() {
//        return laundryCollectionStatus;
//    }
//
//    public void setLaundryCollectionStatus(String laundryCollectionStatus) {
//        this.laundryCollectionStatus = laundryCollectionStatus;
//    }
//
//    public String getLaundryArriveStatus() {
//        return laundryArriveStatus;
//    }
//
//    public void setLaundryArriveStatus(String laundryArriveStatus) {
//        this.laundryArriveStatus = laundryArriveStatus;
//    }
//
//    public String getLaundryCustomerName() {
//        return laundryCustomerName;
//    }
//
//    public void setLaundryCustomerName(String laundryCustomerName) {
//        this.laundryCustomerName = laundryCustomerName;
//    }
//
//    public int getLaundryWeight() {
//        return laundryWeight;
//    }
//
//    public void setLaundryWeight(int laundryWeight) {
//        this.laundryWeight = laundryWeight;
//    }
//
//    public String getLaundryFabricType() {
//        return laundryFabricType;
//    }
//
//    public void setLaundryFabricType(String laundryFabricType) {
//        this.laundryFabricType = laundryFabricType;
//    }
//
//    public Date getLaundryCustomerRegistDate() {
//        return laundryCustomerRegistDate;
//    }
//
//    public void setLaundryCustomerRegistDate(Date laundryCustomerRegistDate) {
//        this.laundryCustomerRegistDate = laundryCustomerRegistDate;
//    }
//
//    public String getLaundryWashingInstructionStatus() {
//        return laundryWashingInstructionStatus;
//    }
//
//    public void setLaundryWashingInstructionStatus(String laundryWashingInstructionStatus) {
//        this.laundryWashingInstructionStatus = laundryWashingInstructionStatus;
//    }
//
//    public Date getLaundryApprovalDate() {
//        return laundryApprovalDate;
//    }
//
//    public void setLaundryApprovalDate(Date laundryApprovalDate) {
//        this.laundryApprovalDate = laundryApprovalDate;
//    }
//
//    public String getLaundryDryCleaningStatus() {
//        return laundryDryCleaningStatus;
//    }
//
//    public void setLaundryDryCleaningStatus(String laundryDryCleaningStatus) {
//        this.laundryDryCleaningStatus = laundryDryCleaningStatus;
//    }
//
//    public int getLaundryDirtyLevel() {
//        return laundryDirtyLevel;
//    }
//
//    public void setLaundryDirtyLevel(int laundryDirtyLevel) {
//        this.laundryDirtyLevel = laundryDirtyLevel;
//    }
//
//    public String getBranchCode() {
//        return branchCode;
//    }
//
//    public void setBranchCode(String branchCode) {
//        this.branchCode = branchCode;
//    }
//
//    public String getDriverLicenseNumber() {
//        return driverLicenseNumber;
//    }
//
//    public void setDriverLicenseNumber(String driverLicenseNumber) {
//        this.driverLicenseNumber = driverLicenseNumber;
//    }
//
//    public Date getLaundryDriverPickupDate() {
//        return laundryDriverPickupDate;
//    }
//
//    public void setLaundryDriverPickupDate(Date laundryDriverPickupDate) {
//        this.laundryDriverPickupDate = laundryDriverPickupDate;
//    }
//
//    public String getLaundryCompleted() {
//        return laundryCompleted;
//    }
//
//    public void setLaundryCompleted(String laundryCompleted) {
//        this.laundryCompleted = laundryCompleted;
//    }
//
//    public Date getLaundryCompletedDate() {
//        return laundryCompletedDate;
//    }
//
//    public void setLaundryCompletedDate(Date laundryCompletedDate) {
//        this.laundryCompletedDate = laundryCompletedDate;
//    }
//
//    @Override
//    public String toString() {
//        return "LaundryDTO{" +
//                "laundryCode=" + laundryCode +
//                ", laundryCollectionStatus='" + laundryCollectionStatus + '\'' +
//                ", laundryArriveStatus='" + laundryArriveStatus + '\'' +
//                ", laundryCustomerName='" + laundryCustomerName + '\'' +
//                ", laundryWeight=" + laundryWeight +
//                ", laundryFabricType='" + laundryFabricType + '\'' +
//                ", laundryCustomerRegistDate=" + laundryCustomerRegistDate +
//                ", laundryWashingInstructionStatus='" + laundryWashingInstructionStatus + '\'' +
//                ", laundryApprovalDate=" + laundryApprovalDate +
//                ", laundryDryCleaningStatus='" + laundryDryCleaningStatus + '\'' +
//                ", laundryDirtyLevel=" + laundryDirtyLevel +
//                ", branchCode='" + branchCode + '\'' +
//                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
//                ", laundryDriverPickupDate=" + laundryDriverPickupDate +
//                ", laundryCompleted='" + laundryCompleted + '\'' +
//                ", laundryCompletedDate='" + laundryCompletedDate + '\'' +
//                '}';
//    }
}
