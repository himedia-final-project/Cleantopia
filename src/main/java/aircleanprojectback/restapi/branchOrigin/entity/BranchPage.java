package aircleanprojectback.restapi.branchOrigin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Entity(name = "branchPage")
@Table(name = "tbl_branch")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class BranchPage {

    @Id
    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "branch_image")
    private String branchImage;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_open_date")
    private Date branchOpenDate;

    @Column(name = "branch_phone")
    private String branchPhone;
    @Column(name = "branch_region")
    private String branchRegion;
    @Column(name = "owner_status")
    private String ownerStatus;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "branch_code")
    private List<BranchOwnerPage> branchOwnerPageList;

}
