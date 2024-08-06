package aircleanprojectback.restapi.facility.dto;

public class FacilityDetailOnlyDTO {

    private int facilityId;
    private String branchCode;
    private int facilityCode;
    private String facilityStatus;

    public FacilityDetailOnlyDTO() {
    }

    public FacilityDetailOnlyDTO(int facilityId, String branchCode, int facilityCode, String facilityStatus) {
        this.facilityId = facilityId;
        this.branchCode = branchCode;
        this.facilityCode = facilityCode;
        this.facilityStatus = facilityStatus;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(int facilityCode) {
        this.facilityCode = facilityCode;
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
                ", branchCode='" + branchCode + '\'' +
                ", facilityCode=" + facilityCode +
                ", facilityStatus='" + facilityStatus + '\'' +
                '}';
    }
}
