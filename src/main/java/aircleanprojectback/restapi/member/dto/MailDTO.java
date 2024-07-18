package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MailDTO {

    private String address;
    private String title;
    private String message;

}
