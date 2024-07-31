package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_laundry_part_management")
@AllArgsConstructor
@Getter
@ToString
public class LaundryPartManagement {

    @Id
    @Column(name = "laundry_part_management_code", length = 100, nullable = false)
    private String laundryPartManagementCode;

    @Column(name = "branch_code", length = 100)
    private String branchCode;

    @Column(name = "headquarters_code", length = 50)
    private String headquartersCode;

    @Column(name = "laundry_part_stock", nullable = false)
    private int laundryPartStock;

    @Column(name = "laundry_part_max_stock", nullable = false)
    private int laundryPartMaxStock;

    @Column(name = "laundry_part_code", nullable = false)
    private String laundryPartCode;

    public LaundryPartManagement() {}

    public LaundryPartManagement laundryPartManagementCode(String laundryPartManagementCode) {
        this.laundryPartManagementCode = laundryPartManagementCode;
        return this;
    }

    public LaundryPartManagement branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public LaundryPartManagement headquartersCode(String headquartersCode) {
        this.headquartersCode = headquartersCode;
        return this;
    }

    public LaundryPartManagement laundryPartStock(int laundryPartStock) {
        this.laundryPartStock = laundryPartStock;
        return this;
    }

    public LaundryPartManagement laundryPartMaxStock(int laundryPartMaxStock) {
        this.laundryPartMaxStock = laundryPartMaxStock;
        return this;
    }

    public LaundryPartManagement laundryPartCode(String laundryPartCode) {
        this.laundryPartCode = laundryPartCode;
        return this;
    }

    public LaundryPartManagement build() {
        return new LaundryPartManagement(laundryPartManagementCode, branchCode, headquartersCode, laundryPartStock, laundryPartMaxStock, laundryPartCode);
    }

}