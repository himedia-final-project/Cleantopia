package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_driver")
public class MembersAndDriver {

    @Id
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "driver_region")
    private String driverRegion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Members members;
}
