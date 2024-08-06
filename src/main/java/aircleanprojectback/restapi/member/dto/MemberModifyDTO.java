package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberModifyDTO {

    private String memberId;
    private String memberName;
    private String dept;
    private String position;
    private String phone;
    private String email;
    private String address;
    private Boolean isPass;
    private String driverRegion;

}
