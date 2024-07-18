package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String employeeCode;

    @Column(name = "member_id")
    private String memberId;

    public EmployeeCode() {}

}
