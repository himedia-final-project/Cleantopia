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
public class LocationCodeAndHeadStockApplication {

    @Id
    @Column(name = "h_application_code", nullable = false)
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

    @Column(name = "h_application_status")
    private String hApplicationStatus;

    @Column(name = "h_application_date")
    private LocalDate hApplicationDate;

    @Column(name = "h_approver_name")
    private String hApproverName;

    @Column(name = "h_approval_date")
    private LocalDate hApprovalDate;

    @ManyToOne
    @JoinColumn(name = "employee_code")
    private EmployeeCode employeeCode;

    public LocationCodeAndHeadStockApplication() {}

    public LocationCodeAndHeadStockApplication hApplicationCode(int hApplicationCode) {
        this.hApplicationCode = hApplicationCode;
        return this;
    }

    public LocationCodeAndHeadStockApplication hDetergent(int hDetergent) {
        this.hDetergent = hDetergent;
        return this;
    }

    public LocationCodeAndHeadStockApplication hSoftener(int hSoftener) {
        this.hSoftener = hSoftener;
        return this;
    }

    public LocationCodeAndHeadStockApplication hBleach(int hBleach) {
        this.hBleach = hBleach;
        return this;
    }

    public LocationCodeAndHeadStockApplication hRemover(int hRemover) {
        this.hRemover = hRemover;
        return this;
    }

    public LocationCodeAndHeadStockApplication hDrumCleaner(int hDrumCleaner) {
        this.hDrumCleaner = hDrumCleaner;
        return this;
    }

    public LocationCodeAndHeadStockApplication hSheet(int hSheet) {
        this.hSheet = hSheet;
        return this;
    }

    public LocationCodeAndHeadStockApplication hLaundryFilter(int hLaundryFilter) {
        this.hLaundryFilter = hLaundryFilter;
        return this;
    }

    public LocationCodeAndHeadStockApplication hDryerFilter(int hDryerFilter) {
        this.hDryerFilter = hDryerFilter;
        return this;
    }

    public LocationCodeAndHeadStockApplication hDryCleanerFilter(int hDryCleanerFilter) {
        this.hDryCleanerFilter = hDryCleanerFilter;
        return this;
    }

    public LocationCodeAndHeadStockApplication hApplicationStatus(String hApplicationStatus) {
        this.hApplicationStatus = hApplicationStatus;
        return this;
    }

    public LocationCodeAndHeadStockApplication hApplicationDate(LocalDate hApplicationDate) {
        this.hApplicationDate = hApplicationDate;
        return this;
    }

    public LocationCodeAndHeadStockApplication hApproverName(String hApproverName) {
        this.hApproverName = hApproverName;
        return this;
    }

    public LocationCodeAndHeadStockApplication hApprovalDate(LocalDate hApprovalDate) {
        this.hApprovalDate = hApprovalDate;
        return this;
    }

    public LocationCodeAndHeadStockApplication employeeCode(EmployeeCode employeeCode) {
        this.employeeCode = employeeCode;
        return this;
    }

    public LocationCodeAndHeadStockApplication build() {
        return new LocationCodeAndHeadStockApplication(hApplicationCode, hDetergent, hSoftener, hBleach,
                hRemover, hDrumCleaner, hSheet, hLaundryFilter, hDryerFilter, hDryCleanerFilter,
                hApplicationStatus, hApplicationDate, hApproverName, hApprovalDate, employeeCode);
    }

}
