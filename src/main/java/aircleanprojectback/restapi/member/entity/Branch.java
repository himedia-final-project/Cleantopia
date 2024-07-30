package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tbl_branch")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Branch {

    @Id
    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "branch_region")
    private String branchRegion;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_phone")
    private String branchPhone;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "branch_image")
    private String branchImage;

    @Column(name = "branch_open_date")
    private Date branchOpenDate;

    @Column(name = "owner_status")
    private String ownerStatus="N";

    public Branch ownerStatus(String var){
        ownerStatus = var;
        return this;
    }




}
