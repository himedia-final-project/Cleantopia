package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AskDTO {
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberRole;
    private String askDescription;
}
