package aircleanprojectback.restapi.stock.dto;

import java.time.LocalDate;

public class BranchStockApplicationDTO {

    private int bApplicationCode;
    private int bDetergent;
    private int bSoftener;
    private int bBleach;
    private int bRemover;
    private int bDrumCleaner;
    private int bSheet;
    private int bLaundryFilter;
    private int bDryerFilter;
    private int bDryCleanerFilter;
    private String bApplicationStatus;
    private LocalDate bApplicationDate;
    private String bApproverName;
    private LocalDate bApprovalDate;
    private String branchCode;
    private String applicantName;
    private String memberId;

    public BranchStockApplicationDTO() {
    }

    public BranchStockApplicationDTO(int bApplicationCode, int bDetergent, int bSoftener, int bBleach, int bRemover, int bDrumCleaner, int bSheet, int bLaundryFilter, int bDryerFilter, int bDryCleanerFilter, String bApplicationStatus, LocalDate bApplicationDate, String bApproverName, LocalDate bApprovalDate, String branchCode, String applicantName, String memberId) {
        this.bApplicationCode = bApplicationCode;
        this.bDetergent = bDetergent;
        this.bSoftener = bSoftener;
        this.bBleach = bBleach;
        this.bRemover = bRemover;
        this.bDrumCleaner = bDrumCleaner;
        this.bSheet = bSheet;
        this.bLaundryFilter = bLaundryFilter;
        this.bDryerFilter = bDryerFilter;
        this.bDryCleanerFilter = bDryCleanerFilter;
        this.bApplicationStatus = bApplicationStatus;
        this.bApplicationDate = bApplicationDate;
        this.bApproverName = bApproverName;
        this.bApprovalDate = bApprovalDate;
        this.branchCode = branchCode;
        this.applicantName = applicantName;
        this.memberId = memberId;
    }

    public int getbApplicationCode() {
        return bApplicationCode;
    }

    public void setbApplicationCode(int bApplicationCode) {
        this.bApplicationCode = bApplicationCode;
    }

    public int getbDetergent() {
        return bDetergent;
    }

    public void setbDetergent(int bDetergent) {
        this.bDetergent = bDetergent;
    }

    public int getbSoftener() {
        return bSoftener;
    }

    public void setbSoftener(int bSoftener) {
        this.bSoftener = bSoftener;
    }

    public int getbBleach() {
        return bBleach;
    }

    public void setbBleach(int bBleach) {
        this.bBleach = bBleach;
    }

    public int getbRemover() {
        return bRemover;
    }

    public void setbRemover(int bRemover) {
        this.bRemover = bRemover;
    }

    public int getbDrumCleaner() {
        return bDrumCleaner;
    }

    public void setbDrumCleaner(int bDrumCleaner) {
        this.bDrumCleaner = bDrumCleaner;
    }

    public int getbSheet() {
        return bSheet;
    }

    public void setbSheet(int bSheet) {
        this.bSheet = bSheet;
    }

    public int getbLaundryFilter() {
        return bLaundryFilter;
    }

    public void setbLaundryFilter(int bLaundryFilter) {
        this.bLaundryFilter = bLaundryFilter;
    }

    public int getbDryerFilter() {
        return bDryerFilter;
    }

    public void setbDryerFilter(int bDryerFilter) {
        this.bDryerFilter = bDryerFilter;
    }

    public int getbDryCleanerFilter() {
        return bDryCleanerFilter;
    }

    public void setbDryCleanerFilter(int bDryCleanerFilter) {
        this.bDryCleanerFilter = bDryCleanerFilter;
    }

    public String getbApplicationStatus() {
        return bApplicationStatus;
    }

    public void setbApplicationStatus(String bApplicationStatus) {
        this.bApplicationStatus = bApplicationStatus;
    }

    public LocalDate getbApplicationDate() {
        return bApplicationDate;
    }

    public void setbApplicationDate(LocalDate bApplicationDate) {
        this.bApplicationDate = bApplicationDate;
    }

    public String getbApproverName() {
        return bApproverName;
    }

    public void setbApproverName(String bApproverName) {
        this.bApproverName = bApproverName;
    }

    public LocalDate getbApprovalDate() {
        return bApprovalDate;
    }

    public void setbApprovalDate(LocalDate bApprovalDate) {
        this.bApprovalDate = bApprovalDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "BranchStockApplicationDTO{" +
                "bApplicationCode=" + bApplicationCode +
                ", bDetergent=" + bDetergent +
                ", bSoftener=" + bSoftener +
                ", bBleach=" + bBleach +
                ", bRemover=" + bRemover +
                ", bDrumCleaner=" + bDrumCleaner +
                ", bSheet=" + bSheet +
                ", bLaundryFilter=" + bLaundryFilter +
                ", bDryerFilter=" + bDryerFilter +
                ", bDryCleanerFilter=" + bDryCleanerFilter +
                ", bApplicationStatus='" + bApplicationStatus + '\'' +
                ", bApplicationDate=" + bApplicationDate +
                ", bApproverName='" + bApproverName + '\'' +
                ", bApprovalDate=" + bApprovalDate +
                ", branchCode='" + branchCode + '\'' +
                ", applicantName='" + applicantName + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}