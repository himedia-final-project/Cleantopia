package aircleanprojectback.restapi.car.entity;

import aircleanprojectback.restapi.member.entity.Members;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_driver")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DriverAndMember {

    @Id
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "assign_car")
    private String assignCar;

    @Column(name = "driver_region")
    private String driverRegion;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Members members;

}
