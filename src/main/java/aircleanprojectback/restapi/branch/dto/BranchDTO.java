package aircleanprojectback.restapi.branch.dto;

import org.springframework.web.multipart.MultipartFile;

public class BranchDTO {

    private String branchCode;
    private String branchRegion;
    private String branchName;
    private String branchPhone;
    private String branchAddress;
    private String branchImage;
    private String branchOpenDate; // 문자열로 유지
    private String memberId;
    private MultipartFile branchImageFile;

    public BranchDTO() {
    }

    // Getters and Setters
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

    public String getBranchOpenDate() {
        return branchOpenDate;
    }

    public void setBranchOpenDate(String branchOpenDate) {
        this.branchOpenDate = branchOpenDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public MultipartFile getBranchImageFile() {
        return branchImageFile;
    }

    public void setBranchImageFile(MultipartFile branchImageFile) {
        this.branchImageFile = branchImageFile;
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
                ", branchOpenDate='" + branchOpenDate + '\'' +
                ", memberId='" + memberId + '\'' +
                ", branchImageFile=" + branchImageFile +
                '}';
    }
}
