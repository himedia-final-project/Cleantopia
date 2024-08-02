package aircleanprojectback.restapi.car.entity;

import aircleanprojectback.restapi.member.entity.DriverAndCar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "tbl_car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CarAndDriver {
    @Id
    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "branch_region")
    private String branchRegion;

    @Column(name = "car_assigned_status")
    private String carAssignedStatus;

    @Column(name = "car_date")
    private Date carDate;

    @Column(name = "car_etc")
    private String carEtc;

    @Column(name = "car_photo")
    private String carPhoto;

    @OneToOne
    @JoinColumn(name = "driver_license_number")
    private DriverAndMember driverAndMember;
}
