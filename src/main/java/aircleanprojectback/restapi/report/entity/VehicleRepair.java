package aircleanprojectback.restapi.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
        import lombok.*;
        import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
@Entity
@Table(name = "tbl_vehicle_repair_report")
@AllArgsConstructor
@Getter
@ToString
public class VehicleRepair {

    @Id
    @Column(name = "vehicle_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleReportCode; // 본사보고서코드

    @Column(name = "vehicle_report_status", length = 200)
    private String vehicleReportStatus;// 보고서상태

    @Column(name = "vehicle_repair_approve")
    private String vehicleRepairApprove;    // 승인/반려

    @Column(name = "before_vehicle_photo", length = 500, nullable = true)
    private String beforeVehiclePhoto; // 수리전사진

    @Column(name = "after_vehicle_photo", length = 500, nullable = true)
    private String afterVehiclePhoto; // 수리후사진

    @Column(name = "vehicle_remark", length = 200, nullable = true)
    private String vehicleRemark; // 비고

    @Column(name = "vehicle_fuel_cost", nullable = true)
    private int vehicleFuelCost; // 주유비

    @Column(name = "vehicle_regular_inspection", length = 200, nullable = true)
    private int vehicleRegularInspection; // 정기점검

    @Column(name = "vehicle_vehicle_repair_cost", nullable = true)
    private int vehicleVehicleRepairCost; // 수리비

    @Column(name = "vehicle_miscellaneous", length = 200, nullable = true)
    private int vehicleMiscellaneous; // 기타

    @Column(name = "vehicle_type", length = 100)
    private String vehicleType; // 종류 컬럼 추가

    @Column(name = "vehicle_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date vehicleSubmissionDate; // 제출일

    @Column(name = "driver_license_number")
    private String driverLicenseNumber; // 면허번호

    @Column(name = "total_vehicle_repair_cost")
    private int totalVehicleRepairCost; // 총지출금액 (primitive에서 객체 타입으로 변경)

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "car_number")
    private String carNumber;

    public void setVehicleRepairStatus(String vehicleReportStatus) {
        this.vehicleReportStatus = vehicleReportStatus;
    }



    public VehicleRepair() {
    }

    public VehicleRepair vehicleReportCode(String var) {
        vehicleReportCode = Integer.parseInt(var);
        return this;
    }

    public VehicleRepair vehicleReportStatus(String var) {
        vehicleReportStatus = var;
        return this;
    }

    public VehicleRepair vehicleRepairApprove(String var) {
        vehicleRepairApprove = var;
        return this;
    }

    public VehicleRepair beforeVehiclePhoto(String var) {
        beforeVehiclePhoto = var;
        return this;
    }

    public VehicleRepair afterVehiclePhoto(String var) {
        afterVehiclePhoto = var;
        return this;
    }

    public VehicleRepair vehicleRemark(String var) {
        vehicleRemark = var;
        return this;
    }

    public VehicleRepair vehicleFuelCost(int var) {
        vehicleFuelCost = var;
        return this;
    }

    public VehicleRepair vehicleRegularInspection(int var) {
        vehicleRegularInspection = var;
        return this;
    }

    public VehicleRepair vehicleVehicleRepairCost(int var) {
        vehicleVehicleRepairCost = var;
        return this;
    }

    public VehicleRepair vehicleMiscellaneous(int var) {
        vehicleMiscellaneous = var;
        return this;
    }

    public VehicleRepair vehicleType(String var) {
        vehicleType = var;
        return this;
    }

    public VehicleRepair vehicleSubmissionDate(String var) {
        vehicleSubmissionDate = Date.valueOf(var);
        return this;
    }

    public VehicleRepair driverLicenseNumber(String var) {
        driverLicenseNumber = var;
        return this;
    }

    public VehicleRepair totalVehicleRepairCost(String var) {
        totalVehicleRepairCost = Integer.valueOf(var);
        return this;
    }

    public VehicleRepair memberName(String var) {
        memberName = var;
        return this;
    }

    public VehicleRepair carNumber(String var) {
        carNumber = var;
        return this;
    }

    public VehicleRepair build() {
        return new VehicleRepair(
                this.vehicleReportCode,
                this.vehicleReportStatus,
                this.vehicleRepairApprove,
                this.beforeVehiclePhoto,
                this.afterVehiclePhoto,
                this.vehicleRemark,
                this.vehicleFuelCost,
                this.vehicleRegularInspection,
                this.vehicleVehicleRepairCost,
                this.vehicleMiscellaneous,
                this.vehicleType,
                this.vehicleSubmissionDate,
                this.driverLicenseNumber,
                this.totalVehicleRepairCost,
                this.memberName,
                this.carNumber);
    }

    public void setVehicleRepairApprove(String vehicleRepairApprove) {
        this.vehicleRepairApprove = vehicleRepairApprove;
    }


}