package aircleanprojectback.restapi.branch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tbl_facility")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Facility {

    @Id
    @Column(name="facility_code")
    private int facilityCode;

    @Column(name="facility_name")
    private String facilityName;


}
