package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "tbl_brach")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MembersAndBranch {

    @Id
    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "branch_open_date")
    private Date branchOpenDate;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "branch_image")
    private String branchImage;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_phone")
    private String branchPhone;

    @Column(name = "branch_region")
    private String branchRegion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Members members;
}
