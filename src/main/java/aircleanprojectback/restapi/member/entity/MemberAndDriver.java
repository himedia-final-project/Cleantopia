package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "tbl_members")
public class MemberAndDriver {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_phone_number")
    private String memberPhoneNumber;

    @Column(name = "member_role")
    private String memberRole;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_birth_date")
    private Date memberBirthDate;

    @Column(name = "member_gender")
    private String memberGender;

    @Column(name = "member_address")
    private String memberAddress;

    @Column(name = "member_status")
    private String memberStatus;

    @Column(name="member_hire_date")
    private Date memberHireDate;

    @Column(name = "branch_ownership")
    private String branchOwnership = "N";

    @Column(name = "member_image")
    private String memberImage;

    @OneToOne(mappedBy = "memberAndDriver", cascade = CascadeType.PERSIST)
    private DriverAndCar driverAndCar;
}
