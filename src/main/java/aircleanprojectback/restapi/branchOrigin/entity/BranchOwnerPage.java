package aircleanprojectback.restapi.branchOrigin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_branch_owner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BranchOwnerPage {

    @Id
    @Column(name = "owner_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerCode;

    @Column(name = "branch_code")
    private String branchCode;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MembersPage membersPage;


}
