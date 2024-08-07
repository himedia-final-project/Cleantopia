package aircleanprojectback.restapi.member.entity;

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
public class Carpage  {
    @Id
    @Column(name = "car_number",  length = 500)
    private String carNumber;

    @Column(name = "driver_license_number", length = 50)
    private String driverLicenseNumber;

    @Column(name = "car_photo",  length = 255)
    private String carPhoto;

    @Column(name = "car_front_image")
    private String carFrontImage;
    @Column(name = "car_rear_image")
    private String carRearImage;

    @Column(name = "car_assigned_status",  length = 200)
    private String carAssignedStatus;


    @Column(name = "car_date", nullable = false)
    private Date carDate;

    @Column(name = "car_etc", length = 200)
    private String carEtc;

    @Column(name = "branch_region")
    private String branchRegion;

    @OneToOne( cascade = CascadeType.PERSIST)
    @JoinColumn(name = "driver_license_number", referencedColumnName = "driver_license_number", insertable = false, updatable = false)
    @ToString.Exclude
    private DriverAndCar driverAndCar;

    public Carpage driverLicenseNumber(String var){
        driverLicenseNumber = var;
        return this;
    }

    public Carpage carAssignedStatus(String var){
        carAssignedStatus= var;
        return this;
    }
}
