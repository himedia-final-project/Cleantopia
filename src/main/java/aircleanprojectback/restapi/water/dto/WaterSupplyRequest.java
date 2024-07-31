package aircleanprojectback.restapi.water.dto;

import lombok.Data;
import java.util.Map;

@Data
public class WaterSupplyRequest {
    private int waterLevel;
    private Row formattedWaterLevel;
    private String branchCode;
    private int supplyAmount;

    public WaterSupplyRequest() {
    }

    public WaterSupplyRequest(int waterLevel, Row formattedWaterLevel, String branchCode, int supplyAmount) {
        this.waterLevel = waterLevel;
        this.formattedWaterLevel = formattedWaterLevel;
        this.branchCode = branchCode;
        this.supplyAmount = supplyAmount;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public Row getFormattedWaterLevel() {
        return formattedWaterLevel;
    }

    public void setFormattedWaterLevel(Row formattedWaterLevel) {
        this.formattedWaterLevel = formattedWaterLevel;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getSupplyAmount() {
        return supplyAmount;
    }

    public void setSupplyAmount(int supplyAmount) {
        this.supplyAmount = supplyAmount;
    }

    @Override
    public String toString() {
        return "WaterSupplyRequest{" +
                "waterLevel=" + waterLevel +
                ", formattedWaterLevel=" + formattedWaterLevel +
                ", branchCode='" + branchCode + '\'' +
                ", supplyAmount=" + supplyAmount +
                '}';
    }
}
