package aircleanprojectback.restapi.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundrySupplyDTO {

    private String laundrySupplyCode;
    private String laundrySupplyName;
    private int laundrySupplyCost;

}
