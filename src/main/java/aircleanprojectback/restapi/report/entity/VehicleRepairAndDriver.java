package aircleanprojectback.restapi.report.entity;

import aircleanprojectback.restapi.member.entity.Driver;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_vehicle_repair_report")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VehicleRepairAndDriver {

    @Id
    @Column(name = "vehicle_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleReportCode;           // 본사보고서코드

    @Column(name = "vehicle_report_status", length = 200, nullable = false)
    private String vehicleReportStatus;      // 보고서상태

    @Column(name = "vehicle_photo", length = 500, nullable = false)
    private String vehiclePhoto;             // 수리사진

    @Column(name = "vehicle_remark", length = 200, nullable = false)
    private String vehicleRemark;            // 비고

    @Column(name = "vehicle_fuel_cost")
    private int vehicleFuelCost;             // 주유비

    @Column(name = "vehicle_regular_inspection", length = 200, nullable = false)
    private String vehicleRegularInspection; // 정기점검

    @Column(name = "vehicle_vehicle_repair_cost")
    private int vehicleVehicleRepairCost;    // 수리비

    @Column(name = "vehicle_miscellaneous", length = 200, nullable = false)
    private String vehicleMiscellaneous;     // 기타

    @Column(name = "vehicle_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date vehicleSubmissionDate;      // 제출일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_license_number")
    private Driver driver;
}
