package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_branch_stock_application")
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class BranchStockApplication {

    @Id
    @Column(name = "b_application_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bApplicationCode;

    @Column(name = "b_detergent")
    private int bDetergent;

    @Column(name = "b_softener")
    private int bSoftener;

    @Column(name = "b_bleach")
    private int bBleach;

    @Column(name = "b_remover")
    private int bRemover;

    @Column(name = "b_drum_cleaner")
    private int bDrumCleaner;

    @Column(name = "b_sheet")
    private int bSheet;

    @Column(name = "b_laundry_filter")
    private int bLaundryFilter;

    @Column(name = "b_dryer_filter")
    private int bDryerFilter;

    @Column(name = "b_dry_cleaner_filter")
    private int bDryCleanerFilter;

    @Column(name = "b_application_status", length = 20, nullable = false)
    private String bApplicationStatus;

    @Column(name = "b_application_date", nullable = false)
    private LocalDate bApplicationDate;

    @Column(name = "b_approver_name", length = 20)
    private String bApproverName;

    @Column(name = "b_approval_date")
    private LocalDate bApprovalDate;

    @Column(name = "branch_code", length = 100, nullable = false)
    private String branchCode;

    @Column(name = "applicant_name", length = 100)  // 새로 추가된 컬럼
    private String applicantName;

    @Column(name = "member_id")
    private String memberId;

    public BranchStockApplication() {}

    public BranchStockApplication bApplicationCode(int bApplicationCode) {
        this.bApplicationCode = bApplicationCode;
        return this;
    }

    public BranchStockApplication bDetergent(int bDetergent) {
        this.bDetergent = bDetergent;
        return this;
    }

    public BranchStockApplication bSoftener(int bSoftener) {
        this.bSoftener = bSoftener;
        return this;
    }

    public BranchStockApplication bBleach(int bBleach) {
        this.bBleach = bBleach;
        return this;
    }

    public BranchStockApplication bRemover(int bRemover) {
        this.bRemover = bRemover;
        return this;
    }

    public BranchStockApplication bDrumCleaner(int bDrumCleaner) {
        this.bDrumCleaner = bDrumCleaner;
        return this;
    }

    public BranchStockApplication bSheet(int bSheet) {
        this.bSheet = bSheet;
        return this;
    }

    public BranchStockApplication bLaundryFilter(int bLaundryFilter) {
        this.bLaundryFilter = bLaundryFilter;
        return this;
    }

    public BranchStockApplication bDryerFilter(int bDryerFilter) {
        this.bDryerFilter = bDryerFilter;
        return this;
    }

    public BranchStockApplication bDryCleanerFilter(int bDryCleanerFilter) {
        this.bDryCleanerFilter = bDryCleanerFilter;
        return this;
    }

    public BranchStockApplication bApplicationStatus(String bApplicationStatus) {
        this.bApplicationStatus = bApplicationStatus;
        return this;
    }

    public BranchStockApplication bApplicationDate(LocalDate bApplicationDate) {
        this.bApplicationDate = bApplicationDate;
        return this;
    }

    public BranchStockApplication bApproverName(String bApproverName) {
        this.bApproverName = bApproverName;
        return this;
    }

    public BranchStockApplication bApprovalDate(LocalDate bApprovalDate) {
        this.bApprovalDate = bApprovalDate;
        return this;
    }

    public BranchStockApplication branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public BranchStockApplication applicantName(String applicantName) {
        this.applicantName = applicantName;
        return this;
    }

    public BranchStockApplication memberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public BranchStockApplication build() {
        return new BranchStockApplication(bApplicationCode, bDetergent, bSoftener, bBleach,
                bRemover, bDrumCleaner, bSheet, bLaundryFilter, bDryerFilter, bDryCleanerFilter,
                bApplicationStatus, bApplicationDate, bApproverName, bApprovalDate, branchCode, applicantName, memberId);
    }

    public int getbApplicationCode() {
        return bApplicationCode;
    }

    public int getbDetergent() {
        return bDetergent;
    }

    public int getbSoftener() {
        return bSoftener;
    }

    public int getbBleach() {
        return bBleach;
    }

    public int getbRemover() {
        return bRemover;
    }

    public int getbDrumCleaner() {
        return bDrumCleaner;
    }

    public int getbSheet() {
        return bSheet;
    }

    public int getbLaundryFilter() {
        return bLaundryFilter;
    }

    public int getbDryerFilter() {
        return bDryerFilter;
    }

    public int getbDryCleanerFilter() {
        return bDryCleanerFilter;
    }

    public String getbApplicationStatus() {
        return bApplicationStatus;
    }

    public LocalDate getbApplicationDate() {
        return bApplicationDate;
    }

    public String getbApproverName() {
        return bApproverName;
    }

    public LocalDate getbApprovalDate() {
        return bApprovalDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getMemberId() {
        return memberId;
    }
}