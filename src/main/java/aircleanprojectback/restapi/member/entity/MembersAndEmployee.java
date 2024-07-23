package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_employee")
@Getter
@ToString
public class MembersAndEmployee {

    @Id
    @Column(name = "employee_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeCode;

    @OneToOne( cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private Members members;

    @Column(name = "employee_dept")
    private String employeeDept;

    @Column(name = "employee_position")
    private String employeePosition;

    public MembersAndEmployee employeeCode(int var){
        employeeCode=var;
        return this;
    }

    public MembersAndEmployee employeeDept(String var){
        employeeDept=var;
        return this;
    }
    public MembersAndEmployee employeePosition(String var){
        employeePosition = var;
        return this;
    }
    public MembersAndEmployee members(Members var){
        members = var;
        return this;
    }

    public MembersAndEmployee builder(){
        return new MembersAndEmployee(this.employeeCode,this.members,this.employeeDept,this.employeePosition);
    }
}
