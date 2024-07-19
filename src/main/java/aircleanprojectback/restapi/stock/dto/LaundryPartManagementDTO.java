package aircleanprojectback.restapi.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundryPartManagementDTO {

    private String laundryPartManagementCode;
    private String branchCode;
    private String headquartersCode;
    private int laundryPartStock;
    private int laundryPartMaxStock;
    private String laundryPartCode;
}
