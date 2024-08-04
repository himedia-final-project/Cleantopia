package aircleanprojectback.restapi.laundry.dto;

public class LaundryWayDTO {

    private int laundryWayId;
    private int laundryCode;
    private int laundryTime;
    private String laundryPickUpStatus;
    private int laundryDetergentAmount;
    private int laundryWaterAmount;
    private int laundryDryingTime;
    private int laundryDryCleaningTime;

    public LaundryWayDTO() {
    }

    public LaundryWayDTO(int laundryWayId, int laundryCode, int laundryTime, String laundryPickUpStatus, int laundryDetergentAmount, int laundryWaterAmount, int laundryDryingTime, int laundryDryCleaningTime) {
        this.laundryWayId = laundryWayId;
        this.laundryCode = laundryCode;
        this.laundryTime = laundryTime;
        this.laundryPickUpStatus = laundryPickUpStatus;
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

    public int getLaundryCode() {
        return laundryCode;
    }

    public void setLaundryCode(int laundryCode) {
        this.laundryCode = laundryCode;
    }

    public int getLaundryTime() {
        return laundryTime;
    }

    public void setLaundryTime(int laundryTime) {
        this.laundryTime = laundryTime;
    }

    public String getLaundryPickUpStatus() {
        return laundryPickUpStatus;
    }

    public void setLaundryPickUpStatus(String laundryPickUpStatus) {
        this.laundryPickUpStatus = laundryPickUpStatus;
    }

    public int getLaundryDetergentAmount() {
        return laundryDetergentAmount;
    }

    public void setLaundryDetergentAmount(int laundryDetergentAmount) {
        this.laundryDetergentAmount = laundryDetergentAmount;
    }

    public int getLaundryWaterAmount() {
        return laundryWaterAmount;
    }

    public void setLaundryWaterAmount(int laundryWaterAmount) {
        this.laundryWaterAmount = laundryWaterAmount;
    }

    public int getLaundryDryingTime() {
        return laundryDryingTime;
    }

    public void setLaundryDryingTime(int laundryDryingTime) {
        this.laundryDryingTime = laundryDryingTime;
    }

    public int getLaundryDryCleaningTime() {
        return laundryDryCleaningTime;
    }

    public void setLaundryDryCleaningTime(int laundryDryCleaningTime) {
        this.laundryDryCleaningTime = laundryDryCleaningTime;
    }

    @Override
    public String toString() {
        return "LaundryWayDTO{" +
                "laundryWayId=" + laundryWayId +
                ", laundryCode=" + laundryCode +
                ", laundryTime=" + laundryTime +
                ", laundryPickUpStatus='" + laundryPickUpStatus + '\'' +
                ", laundryDetergentAmount=" + laundryDetergentAmount +
                ", laundryWaterAmount=" + laundryWaterAmount +
                ", laundryDryingTime=" + laundryDryingTime +
                ", laundryDryCleaningTime=" + laundryDryCleaningTime +
                '}';
    }
}
