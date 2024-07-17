package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_laundry_supply")
@AllArgsConstructor
@Getter
@ToString
public class LaundrySupply {

    @Id
    @Column(name = "laundry_supply_code", length = 100, nullable = false)
    private String laundrySupplyCode;

    @Column(name = "laundry_supply_name", length = 100, nullable = false)
    private String laundrySupplyName;

    @Column(name = "laundry_supply_cost")
    private int laundrySupplyCost;

    public LaundrySupply() {}

}
