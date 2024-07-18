package aircleanprojectback.restapi.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class PagingResponseDTO {

    private Object data;
    private PageDTO pageInfo;

}
