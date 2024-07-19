package aircleanprojectback.restapi.stock.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembersSubDTO {

    private String memberName;
    private String memberRole;
    private String sub;

}
