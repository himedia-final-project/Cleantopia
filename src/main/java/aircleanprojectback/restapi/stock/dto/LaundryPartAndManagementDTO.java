package aircleanprojectback.restapi.stock.dto;

import lombok.*;

public class LaundryPartAndManagementDTO {

    private String laundryPartManagementCode;
    private String branchCode;
    private String headquartersCode;
    private int laundryPartStock;
    private int laundryPartMaxStock;
    private LaundryPartDTO laundryPart;

    public LaundryPartAndManagementDTO() {
    }

    public LaundryPartAndManagementDTO(String laundryPartManagementCode, String branchCode, String headquartersCode, int laundryPartStock, int laundryPartMaxStock, LaundryPartDTO laundryPart) {
        this.laundryPartManagementCode = laundryPartManagementCode;
        this.branchCode = branchCode;
        this.headquartersCode = headquartersCode;
        this.laundryPartStock = laundryPartStock;
        this.laundryPartMaxStock = laundryPartMaxStock;
        this.laundryPart = laundryPart;
    }

    public String getLaundryPartManagementCode() {
        return laundryPartManagementCode;
    }

    public void setLaundryPartManagementCode(String laundryPartManagementCode) {
        this.laundryPartManagementCode = laundryPartManagementCode;
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

    public int getLaundryPartStock() {
        return laundryPartStock;
    }

    public void setLaundryPartStock(int laundryPartStock) {
        this.laundryPartStock = laundryPartStock;
    }

    public int getLaundryPartMaxStock() {
        return laundryPartMaxStock;
    }

    public void setLaundryPartMaxStock(int laundryPartMaxStock) {
        this.laundryPartMaxStock = laundryPartMaxStock;
    }

    public LaundryPartDTO getLaundryPart() {
        return laundryPart;
    }

    public void setLaundryPart(LaundryPartDTO laundryPart) {
        this.laundryPart = laundryPart;
    }

    @Override
    public String toString() {
        return "LaundryPartAndManagementDTO{" +
                "laundryPartManagementCode='" + laundryPartManagementCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", headquartersCode='" + headquartersCode + '\'' +
                ", laundryPartStock=" + laundryPartStock +
                ", laundryPartMaxStock=" + laundryPartMaxStock +
                ", laundryPart=" + laundryPart +
                '}';
    }
}
