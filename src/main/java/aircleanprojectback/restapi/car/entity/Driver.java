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
@Table(name = "tbl_driver")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Driver {
    @Id
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "assign_car")
    private String assignCar;

    @Column(name = "driver_region")
    private String driverRegion;

    @Column(name = "member_id")
    private String memberId;

    public Driver driverLicenseNumber(String var){
        driverLicenseNumber= var;
        return this;
    };

    public Driver driverRegion(String var){
        driverRegion=var;
        return  this;
    }
}
