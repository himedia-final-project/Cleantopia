package aircleanprojectback.restapi.facility.dto;

import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;

public class LaundryAndLaundryWayDTO {

    private int laundryWayId;
    private LaundryDTO laundry;
    private String laundryDetergentAmount;
    private String laundryDryCleaningTime;
    private String laundryDryingTime;
    private String laundryTime;
    private String laundryWaterAmount;

    public LaundryAndLaundryWayDTO() {
    }

    public LaundryAndLaundryWayDTO(int laundryWayId, LaundryDTO laundry, String laundryDetergentAmount, String laundryDryCleaningTime, String laundryDryingTime, String laundryTime, String laundryWaterAmount) {
        this.laundryWayId = laundryWayId;
        this.laundry = laundry;
        this.laundryDetergentAmount = laundryDetergentAmount;
        this.laundryDryCleaningTime = laundryDryCleaningTime;
        this.laundryDryingTime = laundryDryingTime;
        this.laundryTime = laundryTime;
        this.laundryWaterAmount = laundryWaterAmount;
    }

    public int getLaundryWayId() {
        return laundryWayId;
    }

    public void setLaundryWayId(int laundryWayId) {
        this.laundryWayId = laundryWayId;
    }

    public LaundryDTO getLaundry() {
        return laundry;
    }

    public void setLaundry(LaundryDTO laundry) {
        this.laundry = laundry;
    }

    public String getLaundryDetergentAmount() {
        return laundryDetergentAmount;
    }

    public void setLaundryDetergentAmount(String laundryDetergentAmount) {
        this.laundryDetergentAmount = laundryDetergentAmount;
    }

    public String getLaundryDryCleaningTime() {
        return laundryDryCleaningTime;
    }

    public void setLaundryDryCleaningTime(String laundryDryCleaningTime) {
        this.laundryDryCleaningTime = laundryDryCleaningTime;
    }

    public String getLaundryDryingTime() {
        return laundryDryingTime;
    }

    public void setLaundryDryingTime(String laundryDryingTime) {
        this.laundryDryingTime = laundryDryingTime;
    }

    public String getLaundryTime() {
        return laundryTime;
    }

    public void setLaundryTime(String laundryTime) {
        this.laundryTime = laundryTime;
    }

    public String getLaundryWaterAmount() {
        return laundryWaterAmount;
    }

    public void setLaundryWaterAmount(String laundryWaterAmount) {
        this.laundryWaterAmount = laundryWaterAmount;
    }

    @Override
    public String toString() {
        return "LaundryAndLaundryWayDTO{" +
                "laundryWayId=" + laundryWayId +
                ", laundry=" + laundry +
                ", laundryDetergentAmount='" + laundryDetergentAmount + '\'' +
                ", laundryDryCleaningTime='" + laundryDryCleaningTime + '\'' +
                ", laundryDryingTime='" + laundryDryingTime + '\'' +
                ", laundryTime='" + laundryTime + '\'' +
                ", laundryWaterAmount='" + laundryWaterAmount + '\'' +
                '}';
    }
}
