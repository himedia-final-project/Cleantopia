package aircleanprojectback.restapi.facility.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_facility_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class FacilityDetailOnly {

    @Id
    @Column(name = "facility_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;

    @Column(name = "facility_code")
    private int facilityCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "facility_status")
    private String facilityStatus;

}
