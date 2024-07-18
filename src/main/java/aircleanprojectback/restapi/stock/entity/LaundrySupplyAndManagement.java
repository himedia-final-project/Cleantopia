package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_laundry_supply_management")
@AllArgsConstructor
@Getter
@ToString
public class LaundrySupplyAndManagement {

    @Id
    @Column(name = "laundry_supply_management_code")
    private String laundrySupplyManagementCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "headquarters_code")
    private String headquartersCode;

    @Column(name = "laundry_supply_stock")
    private int laundrySupplyStock;

    @Column(name = "laundry_supply_max_stock")
    private int laundrySupplyMaxStock;

    @ManyToOne
    @JoinColumn(name = "laundry_supply_code")
    private LaundrySupply laundrySupply;

    public LaundrySupplyAndManagement() {}

    public LaundrySupplyAndManagement laundrySupplyManagementCode(String laundrySupplyManagementCode) {
        this.laundrySupplyManagementCode = laundrySupplyManagementCode;
        return this;
    }

    public LaundrySupplyAndManagement branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public LaundrySupplyAndManagement headquartersCode(String headquartersCode) {
        this.headquartersCode = headquartersCode;
        return this;
    }

    public LaundrySupplyAndManagement laundrySupplyStock(int laundrySupplyStock) {
        this.laundrySupplyStock = laundrySupplyStock;
        return this;
    }

    public LaundrySupplyAndManagement laundrySupplyMaxStock(int laundrySupplyMaxStock) {
        this.laundrySupplyMaxStock = laundrySupplyMaxStock;
        return this;
    }

    public LaundrySupplyAndManagement laundrySupply(LaundrySupply laundrySupply) {
        this.laundrySupply = laundrySupply;
        return this;
    }

    public LaundrySupplyAndManagement build() {
        return new LaundrySupplyAndManagement(laundrySupplyManagementCode, branchCode, headquartersCode, laundrySupplyStock, laundrySupplyMaxStock, laundrySupply);
    }
}
