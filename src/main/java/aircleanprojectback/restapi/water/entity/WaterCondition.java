package aircleanprojectback.restapi.water.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_water")
public class WaterCondition {

    @Id
    @Column(name = "site_id")  // 장소
    private String siteId;

    @Column(name = "msr_date")
    private String msrDate;

    @Column(name = "w_temp")  // 수온
    private String wTemp;

    @Column(name = "w_ph") // ph 농도
    private String wPh;

    @Column(name = "w_do")  // 용존산소
    private String wDo;

    @Column(name = "w_tn") // 총질소
    private String wTn;

    @Column(name = "w_tp")
    private String wTp; // 총 인

    @Column(name = "w_toc")
    private String wToc;  // 총 유기 탄소

    @Column(name = "w_phen") // 페놀
    private String wPhen;

    @Column(name = "w_cn") // 시안
    private String wCn;

    public WaterCondition(){}

    public WaterCondition(String siteId, String msrDate, String wTemp, String wPh, String wDo, String wTn, String wTp, String wToc, String wPhen, String wCn) {
        this.siteId = siteId;
        this.msrDate = msrDate;
        this.wTemp = wTemp;
        this.wPh = wPh;
        this.wDo = wDo;
        this.wTn = wTn;
        this.wTp = wTp;
        this.wToc = wToc;
        this.wPhen = wPhen;
        this.wCn = wCn;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public String getwTemp() {
        return wTemp;
    }

    public String getwPh() {
        return wPh;
    }

    public String getwDo() {
        return wDo;
    }

    public String getwTn() {
        return wTn;
    }

    public String getwTp() {
        return wTp;
    }

    public String getwToc() {
        return wToc;
    }

    public String getwPhen() {
        return wPhen;
    }

    public String getwCn() {
        return wCn;
    }

    @Override
    public String toString() {
        return "WaterCondition{" +
                "siteId='" + siteId + '\'' +
                ", msrDate='" + msrDate + '\'' +
                ", wTemp='" + wTemp + '\'' +
                ", wPh='" + wPh + '\'' +
                ", wDo='" + wDo + '\'' +
                ", wTn='" + wTn + '\'' +
                ", wTp='" + wTp + '\'' +
                ", wToc='" + wToc + '\'' +
                ", wPhen='" + wPhen + '\'' +
                ", wCn='" + wCn + '\'' +
                '}';
    }
}


