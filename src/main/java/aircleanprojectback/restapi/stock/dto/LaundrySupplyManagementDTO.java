package aircleanprojectback.restapi.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundrySupplyManagementDTO {

    private String laundrySupplyManagementCode;
    private String branchCode;
    private String headquartersCode;
    private int laundrySupplyStock;
    private  int laundrySupplyMaxStock;
    private LaundrySupplyDTO laundrySupply;

}
