package aircleanprojectback.restapi.report.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_car_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CarMembers {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "car_number")
    private String carNumber;


}