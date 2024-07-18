package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;


@Entity
@Table(name = "tbl_employee")
@Getter
@ToString
public class MembersAndEmployee {

    @Id
    @Column(name = "employee_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeCode;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members members;

    @Column(name = "employee_dept")
    private String employeeDept;

    @Column(name = "employee_position")
    private String employeePosition;



}
