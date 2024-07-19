package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "tbl_members")
@ToString

public class Members {

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
    private String memberBirthDate;

    @Column(name = "member_gender")
    private String memberGender;

    @Column(name = "member_address")
    private String memberAddress;

    @Column(name = "member_status")
    private String memberStatus;

    @Column(name="member_hire_date")
    private Date memberHireDate;

    @Column(name = "member_image")
    private String memberImage;



    public Members memberStatus(String var){
        memberStatus = var;
        return this;
    }

    public Members builder(){
        return new Members(this.memberId,this.memberPassword,this.memberName,
                this.memberPhoneNumber,this.memberRole,this.memberEmail,this.memberBirthDate,
                this.memberGender,this.memberAddress,this.memberStatus,this.memberHireDate,this.memberImage);
    }

}
