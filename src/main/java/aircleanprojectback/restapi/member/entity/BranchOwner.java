package aircleanprojectback.restapi.member.entity;

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

    public BranchOwner members(Members members){
        this.members = members;
        return this;
    }

    public BranchOwner branch(Branch branch){
        this.branch = branch;
        return this;
    }

    public BranchOwner builder(){
        return new BranchOwner(this.ownerCode , this.members, this.branch);
    }
}
