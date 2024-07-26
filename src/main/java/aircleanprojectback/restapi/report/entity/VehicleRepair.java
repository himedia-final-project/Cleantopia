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
@NoArgsConstructor // 기본 생성자 추가
public class VehicleRepair {

    @Id
    @Column(name = "vehicle_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleReportCode; // 본사보고서코드

    @Column(name = "vehicle_report_status", length = 200, nullable = false)
    private String vehicleReportStatus = "접수"; // 보고서상태

    @Column(name = "before_vehicle_photo", length = 500)
    private String beforeVehiclePhoto; // 수리전사진

    @Column(name = "after_vehicle_photo", length = 500)
    private String afterVehiclePhoto; // 수리후사진

    @Column(name = "vehicle_remark", length = 200, nullable = false)
    private String vehicleRemark; // 비고

    @Column(name = "vehicle_fuel_cost")
    private Integer vehicleFuelCost; // 주유비 (primitive에서 객체 타입으로 변경)

    @Column(name = "vehicle_regular_inspection", length = 200, nullable = false)
    private String vehicleRegularInspection; // 정기점검

    @Column(name = "vehicle_vehicle_repair_cost")
    private Integer vehicleVehicleRepairCost; // 수리비 (primitive에서 객체 타입으로 변경)

    @Column(name = "vehicle_miscellaneous", length = 200, nullable = false)
    private String vehicleMiscellaneous; // 기타

    @Column(name = "vehicle_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date vehicleSubmissionDate; // 제출일

    @Column(name = "driver_license_number", length = 200, nullable = false)
    private String driverLicenseNumber; // 면허번호

    @Column(name = "total_vehicle_repair_cost")
    private Integer totalVehicleRepairCost; // 총지출금액 (primitive에서 객체 타입으로 변경)

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "car_number")
    private String carNumber;

    public void setVehicleRepairStatus(String vehicleReportStatus) {
        this.vehicleReportStatus = vehicleReportStatus;
    }


    public void setBeforeVehiclePhoto(String beforeReplaceFileName) {
        this.beforeVehiclePhoto = beforeReplaceFileName;
    }

    public void setAfterVehiclePhoto(String afterReplaceFileName) {
        this.afterVehiclePhoto = afterReplaceFileName;
    }
}