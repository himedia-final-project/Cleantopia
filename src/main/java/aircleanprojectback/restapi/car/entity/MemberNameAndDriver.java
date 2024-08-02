package aircleanprojectback.restapi.car.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_membername_driver")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MemberNameAndDriver {

    @Id
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "member_name")
    private String memberName;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "driver_region")
    private String driverRegion;

    @Column(name = "assign_car")
    private String assignCar;

}
