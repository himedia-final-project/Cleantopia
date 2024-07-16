package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_employee")
public class Employee {

    @Id
    @Column(name = "employee_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeCode;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "employee_dept")
    private String employeeDept;

    @Column(name = "employee_position")
    private String employeePosition;




}
