package aircleanprojectback.restapi.report.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRepairDTO {
    // 본사 차량 수리 보고서

    private int vehicleReportCode;              // 본사보고서코드
    private  String vehicleReportStatus;        // 보고서상태
    private  String beforeVehiclePhoto;         // 수리전사진
    private  String afterVehiclePhoto;          // 수리후사진
    private  String vehicleRemark;              // 비고
    private  int vehicleFuelCost;               // 주유비
    private  String vehicleRegularInspection;   // 정기점검
    private  int vehicleVehicleRepairCost;      // 수리비
    private  String vehicleMiscellaneous;       // 기타
    private Date vehicleSubmissionDate;         // 제출일
    private  String driverLicenseNumber;        // 면허번호
    private int totalVehicleRepairCost;         // 총 수리비 금액
    private  String memberName;
    private  String carNumber;
}
