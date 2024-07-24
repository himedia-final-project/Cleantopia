package aircleanprojectback.restapi.stock.dto;

import lombok.*;

public class LaundrySupplyAndManagementDTO {

    private String laundrySupplyManagementCode;
    private String branchCode;
    private String headquartersCode;
    private int laundrySupplyStock;
    private  int laundrySupplyMaxStock;
    private LaundrySupplyDTO laundrySupply;

    public LaundrySupplyAndManagementDTO() {
    }

    public LaundrySupplyAndManagementDTO(String laundrySupplyManagementCode, String branchCode, String headquartersCode, int laundrySupplyStock, int laundrySupplyMaxStock, LaundrySupplyDTO laundrySupply) {
        this.laundrySupplyManagementCode = laundrySupplyManagementCode;
        this.branchCode = branchCode;
        this.headquartersCode = headquartersCode;
        this.laundrySupplyStock = laundrySupplyStock;
        this.laundrySupplyMaxStock = laundrySupplyMaxStock;
        this.laundrySupply = laundrySupply;
    }

    public String getLaundrySupplyManagementCode() {
        return laundrySupplyManagementCode;
    }

    public void setLaundrySupplyManagementCode(String laundrySupplyManagementCode) {
        this.laundrySupplyManagementCode = laundrySupplyManagementCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getHeadquartersCode() {
        return headquartersCode;
    }

    public void setHeadquartersCode(String headquartersCode) {
        this.headquartersCode = headquartersCode;
    }

    public int getLaundrySupplyStock() {
        return laundrySupplyStock;
    }

    public void setLaundrySupplyStock(int laundrySupplyStock) {
        this.laundrySupplyStock = laundrySupplyStock;
    }

    public int getLaundrySupplyMaxStock() {
        return laundrySupplyMaxStock;
    }

    public void setLaundrySupplyMaxStock(int laundrySupplyMaxStock) {
        this.laundrySupplyMaxStock = laundrySupplyMaxStock;
    }

    public LaundrySupplyDTO getLaundrySupply() {
        return laundrySupply;
    }

    public void setLaundrySupply(LaundrySupplyDTO laundrySupply) {
        this.laundrySupply = laundrySupply;
    }

    @Override
    public String toString() {
        return "LaundrySupplyAndManagementDTO{" +
                "laundrySupplyManagementCode='" + laundrySupplyManagementCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", headquartersCode='" + headquartersCode + '\'' +
                ", laundrySupplyStock=" + laundrySupplyStock +
                ", laundrySupplyMaxStock=" + laundrySupplyMaxStock +
                ", laundrySupply=" + laundrySupply +
                '}';
    }
}
