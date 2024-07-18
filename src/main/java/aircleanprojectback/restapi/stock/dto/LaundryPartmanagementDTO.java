package aircleanprojectback.restapi.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundryPartmanagementDTO {

    private String laundryPartManagementCode;
    private String branchCode;
    private String headquartersCode;
    private int laundryPartStock;
    private int laundryPartMaxStock;
    private LaundryPartDTO laundryPart;
}
