package aircleanprojectback.restapi.member.entity;

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
public class Driver {

    @Id
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "driver_region")
    private String driverRegion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Members members;

    @OneToOne(mappedBy = "driver" , cascade = CascadeType.PERSIST)
    private CarForMember car;
}
