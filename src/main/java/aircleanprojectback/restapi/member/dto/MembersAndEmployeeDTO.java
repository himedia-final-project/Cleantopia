package aircleanprojectback.restapi.member.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MembersAndEmployeeDTO {

    private int employeeCode;
    private String employeeDept;
    private String employeePosition;
    private MemberDTO memberDTO;



}
