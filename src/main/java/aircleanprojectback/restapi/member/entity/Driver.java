package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_driver")
public class Driver {

    @Id
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "driver_region")
    private String driverRegion;

    @Column(name = "member_id")
    private String memberId;


}
