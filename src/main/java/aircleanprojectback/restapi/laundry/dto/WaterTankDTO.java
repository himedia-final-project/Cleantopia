package aircleanprojectback.restapi.laundry.dto;

import jakarta.persistence.Column;

public class WaterTankDTO {

    private int waterTankNo;
    private int waterMaxCapacity;
    private int waterCurCapacity;
    private String branchCode;

    public WaterTankDTO() {
    }

    public WaterTankDTO(int waterTankNo, int waterMaxCapacity, int waterCurCapacity, String branchCode) {
        this.waterTankNo = waterTankNo;
        this.waterMaxCapacity = waterMaxCapacity;
        this.waterCurCapacity = waterCurCapacity;
        this.branchCode = branchCode;
    }

    public int getWaterTankNo() {
        return waterTankNo;
    }

    public void setWaterTankNo(int waterTankNo) {
        this.waterTankNo = waterTankNo;
    }

    public int getWaterMaxCapacity() {
        return waterMaxCapacity;
    }

    public void setWaterMaxCapacity(int waterMaxCapacity) {
        this.waterMaxCapacity = waterMaxCapacity;
    }

    public int getWaterCurCapacity() {
        return waterCurCapacity;
    }

    public void setWaterCurCapacity(int waterCurCapacity) {
        this.waterCurCapacity = waterCurCapacity;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        return "WaterTankDTO{" +
                "waterTankNo=" + waterTankNo +
                ", waterMaxCapacity=" + waterMaxCapacity +
                ", waterCurCapacity=" + waterCurCapacity +
                ", branchCode='" + branchCode + '\'' +
                '}';
    }
}
