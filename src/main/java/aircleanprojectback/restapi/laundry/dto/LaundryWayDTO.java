package aircleanprojectback.restapi.laundry.dto;

public class LaundryWayDTO {

    private int laundryWayId;
    private int laundryCode;
    private String laundryTime;
    private String laundryDetergentAmount;
    private String laundryWaterAmount;
    private String laundryDryingTime;
    private String laundryDryCleaningTime;

    public LaundryWayDTO() {
    }

    public LaundryWayDTO(int laundryWayId, int laundryCode, String laundryTime, String laundryDetergentAmount, String laundryWaterAmount, String laundryDryingTime, String laundryDryCleaningTime) {
        this.laundryWayId = laundryWayId;
        this.laundryCode = laundryCode;
        this.laundryTime = laundryTime;
        this.laundryDetergentAmount = laundryDetergentAmount;
        this.laundryWaterAmount = laundryWaterAmount;
        this.laundryDryingTime = laundryDryingTime;
        this.laundryDryCleaningTime = laundryDryCleaningTime;
    }

    public int getLaundryWayId() {
        return laundryWayId;
    }

    public void setLaundryWayId(int laundryWayId) {
        this.laundryWayId = laundryWayId;
    }

    public long getLaundryCode() {
        return laundryCode;
    }

    public void setLaundryCode(int laundryCode) {
        this.laundryCode = laundryCode;
    }

    public String getLaundryTime() {
        return laundryTime;
    }

    public void setLaundryTime(String laundryTime) {
        this.laundryTime = laundryTime;
    }

    public String getLaundryDetergentAmount() {
        return laundryDetergentAmount;
    }

    public void setLaundryDetergentAmount(String laundryDetergentAmount) {
        this.laundryDetergentAmount = laundryDetergentAmount;
    }

    public String getLaundryWaterAmount() {
        return laundryWaterAmount;
    }

    public void setLaundryWaterAmount(String laundryWaterAmount) {
        this.laundryWaterAmount = laundryWaterAmount;
    }

    public String getLaundryDryingTime() {
        return laundryDryingTime;
    }

    public void setLaundryDryingTime(String laundryDryingTime) {
        this.laundryDryingTime = laundryDryingTime;
    }

    public String getLaundryDryCleaningTime() {
        return laundryDryCleaningTime;
    }

    public void setLaundryDryCleaningTime(String laundryDryCleaningTime) {
        this.laundryDryCleaningTime = laundryDryCleaningTime;
    }

    @Override
    public String toString() {
        return "LaundryWayDTO{" +
                "laundryWayId=" + laundryWayId +
                ", laundryCode=" + laundryCode +
                ", laundryTime='" + laundryTime + '\'' +
                ", laundryDetergentAmount='" + laundryDetergentAmount + '\'' +
                ", laundryWaterAmount='" + laundryWaterAmount + '\'' +
                ", laundryDryingTime='" + laundryDryingTime + '\'' +
                ", laundryDryCleaningTime='" + laundryDryCleaningTime + '\'' +
                '}';
    }
}
