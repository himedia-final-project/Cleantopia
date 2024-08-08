package aircleanprojectback.restapi.car.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Getter

@ToString
@AllArgsConstructor
@Entity
@Table(name = "tbl_car")
public class Car {

    @Id
    @Column(name = "car_number", nullable = false, length = 500)
    private String carNumber; // 차량번호

    @Column(name = "driver_license_number", length = 50)
    private String driverLicenseNumber; // 면허번호

    @Column(name = "car_photo", length = 255)
    private String carPhoto; // 사진

    @Column(name = "car_assigned_status", length = 200)
    private String carAssignedStatus; // 배정여부

    @Temporal(TemporalType.DATE)
    @Column(name = "car_date", nullable = false)
    private Date carDate; // 출고일

    @Column(name = "car_etc", length = 200)
    private String carEtc; // 특이사항

    @Column(name = "branch_region")
    private String branchRegion;        // 지역

    @Column(name = "car_front_image")
    private String carFrontImage;

    @Column(name = "car_rear_image")
    private String carRearImage;




    public Car() {}

    public Car carNumber(String carNumber) {
        this.carNumber = carNumber;
        return this;
    }

    public Car driverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
        return this;
    }

    public Car carPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
        return this;
    }

    public Car carAssignedStatus(String carAssignedStatus) {
        this.carAssignedStatus = carAssignedStatus;
        return this;
    }

    public Car branchRegion(String var){
        branchRegion=var;
        return this;
    }




    public Car carEtc(String carEtc) {
        this.carEtc = carEtc;
        return this;
    }

    public Car build() {return new Car (carNumber,driverLicenseNumber,carPhoto,carAssignedStatus,carDate,carEtc,branchRegion,carFrontImage,carRearImage)    ;}


    public void setDriverLicenseNumber(String driverName) {
    }

    public void setCarAssignedStatus(String assigned) {
    }
}
