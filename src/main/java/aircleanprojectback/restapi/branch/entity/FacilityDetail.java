package aircleanprojectback.restapi.branch.entity;

import aircleanprojectback.restapi.member.entity.Branch;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tbl_facility_detail")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class FacilityDetail {

    @Id
    @Column(name="facility_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="branch_code")
    private Branch branch;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="facility_code")
    private Facility facility;

    @Column(name="facility_status")
    private String facilityStatus;


}
