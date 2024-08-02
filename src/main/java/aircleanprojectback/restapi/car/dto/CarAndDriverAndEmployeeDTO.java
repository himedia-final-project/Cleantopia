package aircleanprojectback.restapi.car.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarAndDriverAndEmployeeDTO {

    private String carNumber; // 차량번호
    private DriverCarDTO driverCarDTO; // 면허번호
    private String carAssignedStatus; // 배정여부
    private Date carDate; // 출고일
    private String carPhoto; // 사진
    private String carEtc; // 특이사항
    private String branchRegion; // 지역
}
