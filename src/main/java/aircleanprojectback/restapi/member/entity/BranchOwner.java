package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_branch_owner")
public class BranchOwner {
    @Id
    @Column(name = "owner_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ownerCode;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members members;

    @ManyToOne
    @JoinColumn(name = "branch_code")
    private Branch branch;
}
