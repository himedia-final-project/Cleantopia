package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlarmDTO {

    private String memberId;
    private String askDescription;
    private String memberEmail;
    private String askStatus;
    private String memberName;
    private String memberImage;
}
