package aircleanprojectback.restapi.stock.dto;

import lombok.*;


public class LaundrySupplyDTO {

    private String laundrySupplyCode;
    private String laundrySupplyName;
    private int laundrySupplyCost;

    public LaundrySupplyDTO() {
    }

    public LaundrySupplyDTO(String laundrySupplyCode, String laundrySupplyName, int laundrySupplyCost) {
        this.laundrySupplyCode = laundrySupplyCode;
        this.laundrySupplyName = laundrySupplyName;
        this.laundrySupplyCost = laundrySupplyCost;
    }

    public String getLaundrySupplyCode() {
        return laundrySupplyCode;
    }

    public void setLaundrySupplyCode(String laundrySupplyCode) {
        this.laundrySupplyCode = laundrySupplyCode;
    }

    public String getLaundrySupplyName() {
        return laundrySupplyName;
    }

    public void setLaundrySupplyName(String laundrySupplyName) {
        this.laundrySupplyName = laundrySupplyName;
    }

    public int getLaundrySupplyCost() {
        return laundrySupplyCost;
    }

    public void setLaundrySupplyCost(int laundrySupplyCost) {
        this.laundrySupplyCost = laundrySupplyCost;
    }

    @Override
    public String toString() {
        return "LaundrySupplyDTO{" +
                "laundrySupplyCode='" + laundrySupplyCode + '\'' +
                ", laundrySupplyName='" + laundrySupplyName + '\'' +
                ", laundrySupplyCost=" + laundrySupplyCost +
                '}';
    }
}