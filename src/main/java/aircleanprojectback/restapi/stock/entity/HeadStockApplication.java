package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_head_stock_application")
@AllArgsConstructor
@Getter
@ToString
public class HeadStockApplication {

    @Id
    @Column(name = "h_application_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hApplicationCode;

    @Column(name = "h_detergent")
    private int hDetergent;

    @Column(name = "h_softener")
    private int hSoftener;

    @Column(name = "h_bleach")
    private int hBleach;

    @Column(name = "h_remover")
    private int hRemover;

    @Column(name = "h_drum_cleaner")
    private int hDrumCleaner;

    @Column(name = "h_sheet")
    private int hSheet;

    @Column(name = "h_laundry_filter")
    private int hLaundryFilter;

    @Column(name = "h_dryer_filter")
    private int hDryerFilter;

    @Column(name = "h_dry_cleaner_filter")
    private int hDryCleanerFilter;

    @Column(name = "h_application_status", length = 20, nullable = false)
    private String hApplicationStatus;

    @Column(name = "h_application_date", nullable = false)
    private LocalDate hApplicationDate;

    @Column(name = "h_approver_name", length = 20)
    private String hApproverName;

    @Column(name = "h_approval_date")
    private LocalDate hApprovalDate;

    @Column(name = "employee_code")
    private int employeeCode;

    public HeadStockApplication() {}

    public HeadStockApplication hApplicationCode(int hApplicationCode) {
        this.hApplicationCode = hApplicationCode;
        return this;
    }

    public HeadStockApplication hDetergent(int hDetergent) {
        this.hDetergent = hDetergent;
        return this;
    }

    public HeadStockApplication hSoftener(int hSoftener) {
        this.hSoftener = hSoftener;
        return this;
    }

    public HeadStockApplication hBleach(int hBleach) {
        this.hBleach = hBleach;
        return this;
    }

    public HeadStockApplication hRemover(int hRemover) {
        this.hRemover = hRemover;
        return this;
    }

    public HeadStockApplication hDrumCleaner(int hDrumCleaner) {
        this.hDrumCleaner = hDrumCleaner;
        return this;
    }

    public HeadStockApplication hSheet(int hSheet) {
        this.hSheet = hSheet;
        return this;
    }

    public HeadStockApplication hLaundryFilter(int hLaundryFilter) {
        this.hLaundryFilter = hLaundryFilter;
        return this;
    }

    public HeadStockApplication hDryerFilter(int hDryerFilter) {
        this.hDryerFilter = hDryerFilter;
        return this;
    }

    public HeadStockApplication hDryCleanerFilter(int hDryCleanerFilter) {
        this.hDryCleanerFilter = hDryCleanerFilter;
        return this;
    }

    public HeadStockApplication hApplicationStatus(String hApplicationStatus) {
        this.hApplicationStatus = hApplicationStatus;
        return this;
    }

    public HeadStockApplication hApplicationDate(LocalDate hApplicationDate) {
        this.hApplicationDate = hApplicationDate;
        return this;
    }

    public HeadStockApplication hApproverName(String hApproverName) {
        this.hApproverName = hApproverName;
        return this;
    }

    public HeadStockApplication hApprovalDate(LocalDate hApprovalDate) {
        this.hApprovalDate = hApprovalDate;
        return this;
    }

    public HeadStockApplication employeeCode(int employeeCode) {
        this.employeeCode = employeeCode;
        return this;
    }

    public HeadStockApplication build() {
        return new HeadStockApplication(hApplicationCode, hDetergent, hSoftener, hBleach,
                hRemover, hDrumCleaner, hSheet, hLaundryFilter, hDryerFilter, hDryCleanerFilter,
                hApplicationStatus, hApplicationDate, hApproverName, hApprovalDate, employeeCode);
    }

}
