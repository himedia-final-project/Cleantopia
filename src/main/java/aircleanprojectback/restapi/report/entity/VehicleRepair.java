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
@Builder(toBuilder = true)
public class VehicleRepair {

    @Id
    @Column(name = "vehicle_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleReportCode;           // 본사보고서코드

    @Column(name = "vehicle_report_status", length = 200, nullable = false)
    private  String vehicleReportStatus;        // 보고서상태

    @Column(name = "vehicle_photo", length = 500, nullable = false)
    private  String vehiclePhoto;               // 수리사진

    @Column(name = "vehicle_remark", length = 200, nullable = false)
    private  String vehicleRemark;              // 비고

    @Column(name = "vehicle_fuel_cost")
    private  int vehicleFuelCost;               // 주유비

    @Column(name = "vehicle_regular_inspection", length = 200, nullable = false)
    private  String vehicleRegularInspection;   // 정기점검

    @Column(name = "vehicle_vehicle_repair_cost")
    private  int vehicleVehicleRepairCost;      // 수리비

    @Column(name = "vehicle_miscellaneous", length = 200, nullable = false)
    private  String vehicleMiscellaneous;       // 기타

    @Column(name = "vehicle_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date vehicleSubmissionDate;         // 제출일

    @Column(name = "driver_license_number", length = 200, nullable = false)
    private  String driverLicenseNumber;        // 면허번호

    public VehicleRepair() {
    }

    public VehicleRepair vehicleReportCode(int vehicleReportCode) {
        this.vehicleReportCode = vehicleReportCode;
        return this;
    }

    public VehicleRepair vehicleReportStatus(String vehicleReportStatus) {
        this.vehicleReportStatus = vehicleReportStatus;
        return this;
    }

    public VehicleRepair vehiclePhoto(String vehiclePhoto) {
        this.vehiclePhoto = vehiclePhoto;
        return this;
    }

    public VehicleRepair vehicleRemark(String vehicleRemark) {
        this.vehicleRemark = vehicleRemark;
        return this;
    }

    public VehicleRepair vehicleFuelCost(int vehicleFuelCost) {
        this.vehicleFuelCost = vehicleFuelCost;
        return this;
    }

    public VehicleRepair vehicleRegularInspection(String vehicleRegularInspection) {
        this.vehicleRegularInspection = vehicleRegularInspection;
        return this;
    }

    public VehicleRepair vehicleVehicleRepairCost(int vehicleVehicleRepairCost) {
        this.vehicleVehicleRepairCost = vehicleVehicleRepairCost;
        return this;
    }

    public VehicleRepair vehicleMiscellaneous(String vehicleMiscellaneous) {
        this.vehicleMiscellaneous = vehicleMiscellaneous;
        return this;
    }

    public VehicleRepair vehicleSubmissionDate(Date vehicleSubmissionDate) {
        this.vehicleSubmissionDate = vehicleSubmissionDate;
        return this;
    }

    public VehicleRepair driverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
        return this;
    }


    public VehicleRepair build(){
        return  new VehicleRepair(vehicleReportCode, vehicleReportStatus, vehiclePhoto, vehicleRemark, vehicleFuelCost, vehicleRegularInspection, vehicleVehicleRepairCost,vehicleMiscellaneous, vehicleSubmissionDate, driverLicenseNumber);
    }
}
