package aircleanprojectback.restapi.water.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Row {
    @JsonProperty("MSR_DATE")
    private String msrDate;

    @JsonProperty("MSR_TIME")
    private String msrTime;

    @JsonProperty("SITE_ID")
    private String siteId;

    @JsonProperty("W_TEMP")
    private String wTemp;

    @JsonProperty("W_PH")
    private String wPh;

    @JsonProperty("W_DO")
    private String wDo;

    @JsonProperty("W_TN")
    private String wTn;

    @JsonProperty("W_TP")
    private String wTp;

    @JsonProperty("W_TOC")
    private String wToc;

    @JsonProperty("W_PHEN")
    private String wPhen;

    @JsonProperty("W_CN")
    private String wCn;
}
