package aircleanprojectback.restapi.branch.dto;

import java.sql.Date;

public class BranchDTO {

    private String branchCode;
    private String branchRegion;
    private String branchName;
    private String branchPhone;
    private String branchAddress;
    private String branchImage;
    private Date branchOpenDate;
    private String memberId;

    public BranchDTO() {
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchRegion() {
        return branchRegion;
    }

    public void setBranchRegion(String branchRegion) {
        this.branchRegion = branchRegion;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchImage() {
        return branchImage;
    }

    public void setBranchImage(String branchImage) {
        this.branchImage = branchImage;
    }

    public Date getBranchOpenDate() {
        return branchOpenDate;
    }

    public void setBranchOpenDate(Date branchOpenDate) {
        this.branchOpenDate = branchOpenDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "branchCode='" + branchCode + '\'' +
                ", branchRegion='" + branchRegion + '\'' +
                ", branchName='" + branchName + '\'' +
                ", branchPhone='" + branchPhone + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", branchImage='" + branchImage + '\'' +
                ", branchOpenDate=" + branchOpenDate +
                ", memberId='" + memberId + '\'' +
                '}';
    }
    
}
