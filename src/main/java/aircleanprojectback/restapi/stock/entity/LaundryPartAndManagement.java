package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_laundry_part_management")
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class LaundryPartAndManagement {

    @Id
    @Column(name = "laundry_part_management_code")
    private String laundryPartManagementCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "headquarters_code")
    private String headquartersCode;

    @Column(name = "laundry_part_stock")
    private int laundryPartStock;

    @Column(name = "laundry_part_max_stock")
    private int laundryPartMaxStock;

    @ManyToOne
    @JoinColumn(name = "laundry_part_code")
    private LaundryPart laundryPart;

    public LaundryPartAndManagement() {}

    public LaundryPartAndManagement laundryPartManagementCode(String laundryPartManagementCode) {
        this.laundryPartManagementCode = laundryPartManagementCode;
        return this;
    }

    public LaundryPartAndManagement branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public LaundryPartAndManagement headquartersCode(String headquartersCode) {
        this.headquartersCode = headquartersCode;
        return this;
    }

    public LaundryPartAndManagement laundryPartStock(int laundryPartStock) {
        this.laundryPartStock = laundryPartStock;
        return this;
    }

    public LaundryPartAndManagement laundryPartMaxStock(int laundryPartMaxStock) {
        this.laundryPartMaxStock = laundryPartMaxStock;
        return this;
    }

    public LaundryPartAndManagement laundryPart(LaundryPart laundryPart) {
        this.laundryPart = laundryPart;
        return this;
    }

    public LaundryPartAndManagement build() {
        return new LaundryPartAndManagement(laundryPartManagementCode, branchCode, headquartersCode, laundryPartStock, laundryPartMaxStock, laundryPart);
    }

    public String getLaundryPartManagementCode() {
        return laundryPartManagementCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getHeadquartersCode() {
        return headquartersCode;
    }

    public int getLaundryPartStock() {
        return laundryPartStock;
    }

    public int getLaundryPartMaxStock() {
        return laundryPartMaxStock;
    }

    public LaundryPart getLaundryPart() {
        return laundryPart;
    }
}