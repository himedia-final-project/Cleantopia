package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "tbl_branch")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    @Column(name = "member_id")
    private String memberId;



}
