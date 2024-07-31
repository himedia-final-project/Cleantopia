package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_employee")
@AllArgsConstructor
@Getter
@ToString
public class EmployeeCode {

    @Id
    @Column(name = "employee_code", length = 50, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeCode;

    @Column(name = "member_id")
    private String memberId;

    public EmployeeCode() {}

}