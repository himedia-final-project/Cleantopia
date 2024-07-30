package aircleanprojectback.restapi.water.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Result {


    @JsonProperty("CODE")
    private String code;

    @JsonProperty("MESSAGE")
    private String message;


}

