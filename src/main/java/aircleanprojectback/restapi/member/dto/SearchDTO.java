package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchDTO {

    private String category;
    private String value;
}
