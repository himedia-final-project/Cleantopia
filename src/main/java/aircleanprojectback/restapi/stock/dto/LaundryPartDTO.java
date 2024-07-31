package aircleanprojectback.restapi.stock.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundryPartDTO {

    private String laundryPartCode;
    private String laundryPartName;
    private int laundryPartCost;

}
