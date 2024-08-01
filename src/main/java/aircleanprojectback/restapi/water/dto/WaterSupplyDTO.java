package aircleanprojectback.restapi.water.dto;

import aircleanprojectback.restapi.water.entity.WaterSupply;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class WaterSupplyDTO {

    private int waterSupplyCode;
    private String msrDate;
    private String siteId;
    private String wTemp;
    private String wPh;
    private String wDo;
    private String wTn;
    private String wTp;
    private String wCn;
    private String wPhen;
    private String waterVolume;
    private String waterTankNo;

    public WaterSupplyDTO() {
    }

    public WaterSupplyDTO(int waterSupplyCode, String msrDate, String siteId, String wTemp, String wPh, String wDo, String wTn, String wTp, String wCn, String wPhen, String waterVolume, String waterTankNo) {
        this.waterSupplyCode = waterSupplyCode;
        this.msrDate = msrDate;
        this.siteId = siteId;
        this.wTemp = wTemp;
        this.wPh = wPh;
        this.wDo = wDo;
        this.wTn = wTn;
        this.wTp = wTp;
        this.wCn = wCn;
        this.wPhen = wPhen;
        this.waterVolume = waterVolume;
        this.waterTankNo = waterTankNo;
    }

    public int getWaterSupplyCode() {
        return waterSupplyCode;
    }

    public void setWaterSupplyCode(int waterSupplyCode) {
        this.waterSupplyCode = waterSupplyCode;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getwTemp() {
        return wTemp;
    }

    public void setwTemp(String wTemp) {
        this.wTemp = wTemp;
    }

    public String getwPh() {
        return wPh;
    }

    public void setwPh(String wPh) {
        this.wPh = wPh;
    }

    public String getwDo() {
        return wDo;
    }

    public void setwDo(String wDo) {
        this.wDo = wDo;
    }

    public String getwTn() {
        return wTn;
    }

    public void setwTn(String wTn) {
        this.wTn = wTn;
    }

    public String getwTp() {
        return wTp;
    }

    public void setwTp(String wTp) {
        this.wTp = wTp;
    }

    public String getwCn() {
        return wCn;
    }

    public void setwCn(String wCn) {
        this.wCn = wCn;
    }

    public String getwPhen() {
        return wPhen;
    }

    public void setwPhen(String wPhen) {
        this.wPhen = wPhen;
    }

    public String getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(String waterVolume) {
        this.waterVolume = waterVolume;
    }

    public String getWaterTankNo() {
        return waterTankNo;
    }

    public void setWaterTankNo(String waterTankNo) {
        this.waterTankNo = waterTankNo;
    }

    @Override
    public String toString() {
        return "WaterSupplyDTO{" +
                "waterSupplyCode=" + waterSupplyCode +
                ", msrDate='" + msrDate + '\'' +
                ", siteId='" + siteId + '\'' +
                ", wTemp='" + wTemp + '\'' +
                ", wPh='" + wPh + '\'' +
                ", wDo='" + wDo + '\'' +
                ", wTn='" + wTn + '\'' +
                ", wTp='" + wTp + '\'' +
                ", wCn='" + wCn + '\'' +
                ", wPhen='" + wPhen + '\'' +
                ", waterVolume='" + waterVolume + '\'' +
                ", waterTankNo='" + waterTankNo + '\'' +
                '}';
    }

    public WaterSupply toEntity() {
        return WaterSupply.builder()
                .waterSupplyCode(this.waterSupplyCode)
                .msrDate(this.msrDate)
                .siteId(this.siteId)
                .wTemp(this.wTemp)
                .wPh(this.wPh)
                .wDo(this.wDo)
                .wTn(this.wTn)
                .wTp(this.wTp)
                .wCn(this.wCn)
                .wPhen(this.wPhen)
                .waterVolume(this.waterVolume)
                .waterTankNo(this.waterTankNo)
                .build();
    }
}
