package aircleanprojectback.restapi.car.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

    private String carNumber; // 차량번호
    private String driverName; // 운전자 이름
    private String carAssignedStatus; // 배정여부
    private Date carDate; // 출고일
    private String carPhoto; // 차량 사진
    private String carEtc; // 특이사항
    private String branchRegion;



}

