package aircleanprojectback.restapi.facility.dto;

public class FacilityDetailOnlyDTO {

    private int facilityId;
    private int facilityCode;
    private String branchCode;
    private String facilityStatus;

    public FacilityDetailOnlyDTO() {
    }

    public FacilityDetailOnlyDTO(int facilityId, int facilityCode, String branchCode, String facilityStatus) {
        this.facilityId = facilityId;
        this.facilityCode = facilityCode;
        this.branchCode = branchCode;
        this.facilityStatus = facilityStatus;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public int getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(int facilityCode) {
        this.facilityCode = facilityCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getFacilityStatus() {
        return facilityStatus;
    }

    public void setFacilityStatus(String facilityStatus) {
        this.facilityStatus = facilityStatus;
    }

    @Override
    public String toString() {
        return "FacilityDetailOnlyDTO{" +
                "facilityId=" + facilityId +
                ", facilityCode=" + facilityCode +
                ", branchCode='" + branchCode + '\'' +
                ", facilityStatus='" + facilityStatus + '\'' +
                '}';
    }
}
