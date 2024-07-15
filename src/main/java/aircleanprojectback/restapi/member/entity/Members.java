package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor@Getter
@Table(name = "members")
@ToString
public class Members {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_phone")
    private String memberPhone;

    @Column(name = "member_role")
    private String memberRole;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "member_address")
    private String memberAddress;

    @Column(name = "memberStatus")
    private String memberStatus;

    @Column(name="hire_date")
    private Date hireDate;




}
