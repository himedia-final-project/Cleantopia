package aircleanprojectback.restapi.mainpage.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RankingDTO {

    private String memberName;
    private String memberImage;
    private String branchName;
    private long totalRevenue;
    private int rankIndex;

}
