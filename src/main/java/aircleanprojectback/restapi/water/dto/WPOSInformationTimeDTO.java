package aircleanprojectback.restapi.water.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WPOSInformationTimeDTO {

    @JsonProperty("WPOSInformationTime")
    private WPOSInformationTimeData data;
}
