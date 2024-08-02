package aircleanprojectback.restapi.member.entity;

import aircleanprojectback.restapi.car.entity.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "tbl_driver")
public class DriverAndCar {
    @Id
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "driver_region")
    private String driverRegion;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "assign_car")
    private String assignCar = "N";

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    @ToString.Exclude
    private MemberAndDriver memberAndDriver;

    @OneToOne(mappedBy = "driverAndCar" , cascade = CascadeType.PERSIST)
    @JoinColumn(name = "driver_license_number")
    private Carpage car;

    public DriverAndCar assignCar(String var){
        assignCar=var;
        return this;
    }

}
