package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "tbl_car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CarForMember {

    @Id
    @Column(name = "car_number", nullable = false, length = 500)
    private String carNumber; // 차량번호

    @OneToOne
    @JoinColumn(name = "driver_license_number")
    @ToString.Exclude
    private Driver driver;// 면허번호

    @Column(name = "car_photo", nullable = false, length = 255)
    private String carPhoto; // 사진

    @Column(name = "car_assigned_status", nullable = false, length = 200)
    private String carAssignedStatus; // 배정여부

    @Temporal(TemporalType.DATE)
    @Column(name = "car_date", nullable = false)
    private Date carDate; // 출고일

    @Column(name = "car_etc", length = 200)
    private String carEtc; // 특이사항

    @Column(name = "branch_region")
    private String branchRegion;
}
