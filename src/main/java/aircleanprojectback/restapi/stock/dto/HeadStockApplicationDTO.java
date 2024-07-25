package aircleanprojectback.restapi.stock.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;


public class HeadStockApplicationDTO {

    private int hApplicationCode;
    private int hDetergent;
    private int hSoftener;
    private int hBleach;
    private int hRemover;
    private int hDrumCleaner;
    private int hSheet;
    private int hLaundryFilter;
    private int hDryerFilter;
    private int hDryCleanerFilter;
    private String hApplicationStatus;
    private LocalDate hApplicationDate;
    private String hApproverName;
    private LocalDate hApprovalDate;
    private String memberId;
    private String hApplicantName;

    public HeadStockApplicationDTO() {
    }

    public HeadStockApplicationDTO(int hApplicationCode, int hDetergent, int hSoftener, int hBleach, int hRemover, int hDrumCleaner, int hSheet, int hLaundryFilter, int hDryerFilter, int hDryCleanerFilter, String hApplicationStatus, LocalDate hApplicationDate, String hApproverName, LocalDate hApprovalDate, String memberId, String hApplicantName) {
        this.hApplicationCode = hApplicationCode;
        this.hDetergent = hDetergent;
        this.hSoftener = hSoftener;
        this.hBleach = hBleach;
        this.hRemover = hRemover;
        this.hDrumCleaner = hDrumCleaner;
        this.hSheet = hSheet;
        this.hLaundryFilter = hLaundryFilter;
        this.hDryerFilter = hDryerFilter;
        this.hDryCleanerFilter = hDryCleanerFilter;
        this.hApplicationStatus = hApplicationStatus;
        this.hApplicationDate = hApplicationDate;
        this.hApproverName = hApproverName;
        this.hApprovalDate = hApprovalDate;
        this.memberId = memberId;
        this.hApplicantName = hApplicantName;
    }

    public int gethApplicationCode() {
        return hApplicationCode;
    }

    public void sethApplicationCode(int hApplicationCode) {
        this.hApplicationCode = hApplicationCode;
    }

    public int gethDetergent() {
        return hDetergent;
    }

    public void sethDetergent(int hDetergent) {
        this.hDetergent = hDetergent;
    }

    public int gethSoftener() {
        return hSoftener;
    }

    public void sethSoftener(int hSoftener) {
        this.hSoftener = hSoftener;
    }

    public int gethBleach() {
        return hBleach;
    }

    public void sethBleach(int hBleach) {
        this.hBleach = hBleach;
    }

    public int gethRemover() {
        return hRemover;
    }

    public void sethRemover(int hRemover) {
        this.hRemover = hRemover;
    }

    public int gethDrumCleaner() {
        return hDrumCleaner;
    }

    public void sethDrumCleaner(int hDrumCleaner) {
        this.hDrumCleaner = hDrumCleaner;
    }

    public int gethSheet() {
        return hSheet;
    }

    public void sethSheet(int hSheet) {
        this.hSheet = hSheet;
    }

    public int gethLaundryFilter() {
        return hLaundryFilter;
    }

    public void sethLaundryFilter(int hLaundryFilter) {
        this.hLaundryFilter = hLaundryFilter;
    }

    public int gethDryerFilter() {
        return hDryerFilter;
    }

    public void sethDryerFilter(int hDryerFilter) {
        this.hDryerFilter = hDryerFilter;
    }

    public int gethDryCleanerFilter() {
        return hDryCleanerFilter;
    }

    public void sethDryCleanerFilter(int hDryCleanerFilter) {
        this.hDryCleanerFilter = hDryCleanerFilter;
    }

    public String gethApplicationStatus() {
        return hApplicationStatus;
    }

    public void sethApplicationStatus(String hApplicationStatus) {
        this.hApplicationStatus = hApplicationStatus;
    }

    public LocalDate gethApplicationDate() {
        return hApplicationDate;
    }

    public void sethApplicationDate(LocalDate hApplicationDate) {
        this.hApplicationDate = hApplicationDate;
    }

    public String gethApproverName() {
        return hApproverName;
    }

    public void sethApproverName(String hApproverName) {
        this.hApproverName = hApproverName;
    }

    public LocalDate gethApprovalDate() {
        return hApprovalDate;
    }

    public void sethApprovalDate(LocalDate hApprovalDate) {
        this.hApprovalDate = hApprovalDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String gethApplicantName() {
        return hApplicantName;
    }

    public void sethApplicantName(String hApplicantName) {
        this.hApplicantName = hApplicantName;
    }

    @Override
    public String toString() {
        return "HeadStockApplicationDTO{" +
                "hApplicationCode=" + hApplicationCode +
                ", hDetergent=" + hDetergent +
                ", hSoftener=" + hSoftener +
                ", hBleach=" + hBleach +
                ", hRemover=" + hRemover +
                ", hDrumCleaner=" + hDrumCleaner +
                ", hSheet=" + hSheet +
                ", hLaundryFilter=" + hLaundryFilter +
                ", hDryerFilter=" + hDryerFilter +
                ", hDryCleanerFilter=" + hDryCleanerFilter +
                ", hApplicationStatus='" + hApplicationStatus + '\'' +
                ", hApplicationDate=" + hApplicationDate +
                ", hApproverName='" + hApproverName + '\'' +
                ", hApprovalDate=" + hApprovalDate +
                ", memberId='" + memberId + '\'' +
                ", hApplicantName='" + hApplicantName + '\'' +
                '}';
    }
}

