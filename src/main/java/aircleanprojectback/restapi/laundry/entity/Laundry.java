package aircleanprojectback.restapi.laundry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_laundry")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@ToString
public class Laundry {

    @Id
    @Column(name = "laundry_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int laundryCode;

    // 도착여부(승낙)
    @Column(name = "laundry_collection_status")
    private String laundryCollectionStatus;

    // 수거상태
    @Column(name = "laundry_arrive_status")
    private String laundryArriveStatus;

    // 고객이름
    @Column(name = "laundry_customer_name")
    private String laundryCustomerName;

    // 세탁물 무게
    @Column(name = "laundry_weight")
    private int laundryWeight;

    // 옷감
    @Column(name = "laundry_fabric_type")
    private String laundryFabricType;

    // 고객 등록일
    @Column(name = "laundry_customer_regist_date")
    private Date laundryCustomerRegistDate;

    // 도출 상태
    @Column(name = "laundry_washing_instruction_status")
    private String laundryWashingInstructionStatus;

    // 승낙일
    @Column(name = "laundry_approval_date")
    private Date laundryApprovalDate;

    // 드라이 클리닝 여부
    @Column(name = "laundry_dry_cleaning_status")
    private String laundryDryCleaningStatus;

    // 더러운 정도
    @Column(name = "laundry_dirty_level")
    private int laundryDirtyLevel;

    // 지점 코드
    @Column(name = "branch_code")
    private String branchCode;

    // 배송기사 라이센스 번호
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    // 배송기사 픽업날짜
    @Column(name = "laundry_driver_pickup_date")
    private Date laundryDriverPickupDate;

    // 세탁 완료 상태
    @Column(name = "laundry_completed_state")
    private String laundryCompleted;


}
