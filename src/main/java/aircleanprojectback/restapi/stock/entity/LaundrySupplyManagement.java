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
public class LaundrySupplyManagement {

    @Id
    @Column(name = "laundry_supply_management_code", length = 100, nullable = false)
    private String laundrySupplyManagementCode;

    @Column(name = "branch_code", length = 100)
    private String branchCode;

    @Column(name = "headquarters_code", length = 100)
    private String headquartersCode;

    @Column(name = "laundry_supply_stock")
    private int laundrySupplyStock;

    @Column(name = "laundry_supply_max_stock")
    private int laundrySupplyMaxStock;

    @ManyToOne
    @JoinColumn(name = "laundry_supply_code")
    private LaundrySupply laundrySupply;

    public LaundrySupplyManagement() {}

    public LaundrySupplyManagement laundrySupplyManagementCode(String laundrySupplyManagementCode) {
        this.laundrySupplyManagementCode = laundrySupplyManagementCode;
        return this;
    }

    public LaundrySupplyManagement branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public LaundrySupplyManagement headquartersCode(String headquartersCode) {
        this.headquartersCode = headquartersCode;
        return this;
    }

    public LaundrySupplyManagement laundrySupplyStock(int laundrySupplyStock) {
        this.laundrySupplyStock = laundrySupplyStock;
        return this;
    }

    public LaundrySupplyManagement laundrySupplyMaxStock(int laundrySupplyMaxStock) {
        this.laundrySupplyMaxStock = laundrySupplyMaxStock;
        return this;
    }

    public LaundrySupplyManagement laundrySupply(LaundrySupply laundrySupply) {
        this.laundrySupply = laundrySupply;
        return this;
    }

    public LaundrySupplyManagement build() {
        return new LaundrySupplyManagement(laundrySupplyManagementCode, branchCode, headquartersCode, laundrySupplyStock, laundrySupplyMaxStock, laundrySupply);
    }
}
