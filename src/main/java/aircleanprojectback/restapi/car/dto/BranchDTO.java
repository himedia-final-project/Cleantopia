package aircleanprojectback.restapi.car.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDTO {

    private String branchCode; // 지점코드
    private String branchName; // 지점 이름
    private String branchRegion; // 지점 지역
    private String branchImage; // 지점 사진
}