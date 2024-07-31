package aircleanprojectback.restapi.stock.dto;

import lombok.*;


public class LaundryPartDTO {

    private String laundryPartCode;
    private String laundryPartName;
    private int laundryPartCost;

    public LaundryPartDTO() {
    }

    public LaundryPartDTO(String laundryPartCode, String laundryPartName, int laundryPartCost) {
        this.laundryPartCode = laundryPartCode;
        this.laundryPartName = laundryPartName;
        this.laundryPartCost = laundryPartCost;
    }

    public String getLaundryPartCode() {
        return laundryPartCode;
    }

    public void setLaundryPartCode(String laundryPartCode) {
        this.laundryPartCode = laundryPartCode;
    }

    public String getLaundryPartName() {
        return laundryPartName;
    }

    public void setLaundryPartName(String laundryPartName) {
        this.laundryPartName = laundryPartName;
    }

    public int getLaundryPartCost() {
        return laundryPartCost;
    }

    public void setLaundryPartCost(int laundryPartCost) {
        this.laundryPartCost = laundryPartCost;
    }

    @Override
    public String toString() {
        return "LaundryPartDTO{" +
                "laundryPartCode='" + laundryPartCode + '\'' +
                ", laundryPartName='" + laundryPartName + '\'' +
                ", laundryPartCost=" + laundryPartCost +
                '}';
    }
}
