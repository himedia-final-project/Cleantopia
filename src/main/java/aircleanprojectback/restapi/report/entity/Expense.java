package aircleanprojectback.restapi.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_expense_report")
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class Expense {

    @Id
    @Column(name = "expense_report_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseReportCode;       // 지점지출보고서코드

    @Column(name = "electricity_bill")
    private int electricityBill;            // 전기세

    @Column(name = "water_bill")
    private  int waterBill;                 // 수도세

    @Column(name = "gas_bill")
    private int gasBill;                    // 가스비

    @Column(name = "part_time_salary")
    private  int partTimeSalary;            // 알바비

    @Column(name = "expense_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date expenseSubmissionDate;    // 제출일

    @Column(name = "expense_report_status", length = 200, nullable = false)
    private String expenseReportStatus = "접수";     // 보고서상태

    @Column(name = "branch_code", length = 200, nullable = false)
    private String branchCode;              // 지점코드

    public Expense() {
        this.expenseReportStatus = "접수";
    }

    public Expense expenseReportCode(int expenseReportCode) {
        this.expenseReportCode = expenseReportCode;
        return this;
    }

    public Expense electricityBill(int electricityBill) {
        this.electricityBill = electricityBill;
        return this;
    }

    public Expense waterBill(int waterBill) {
        this.waterBill = waterBill;
        return this;
    }

    public Expense gasBill(int gasBill) {
        this.gasBill = gasBill;
        return this;
    }

    public Expense partTimeSalary(int partTimeSalary) {
        this.partTimeSalary = partTimeSalary;
        return this;
    }

    public Expense expenseSubmissionDate(Date expenseSubmissionDate) {
        this.expenseSubmissionDate = expenseSubmissionDate;
        return this;
    }

    public Expense expenseReportStatus(String expenseReportStatus) {
        this.expenseReportStatus = expenseReportStatus;
        return this;
    }

    public Expense branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public Expense build(){
        return new Expense(expenseReportCode,electricityBill, waterBill, gasBill, partTimeSalary, expenseSubmissionDate, expenseReportStatus, branchCode);
    }

    public void setExpenseReportStatus(String expenseReportStatus) {
        this.expenseReportStatus = expenseReportStatus;
    }
}
