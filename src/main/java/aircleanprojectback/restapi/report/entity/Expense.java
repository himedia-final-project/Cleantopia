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

    @Column(name = "repair_cost")
    private int repairCost;                 // 시설물수리비

    @Column(name = "expense_submission_date")
    @Temporal(TemporalType.DATE) // 날짜만 저장 (시간 제외)
    @CreationTimestamp // 엔티티가 생성될 때 현재 날짜로 설정
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // JSON 직렬화/역직렬화 형식 지정
    private Date expenseSubmissionDate;    // 제출일

    @Column(name = "month_date")
    private String monthDate;         //월에 쓴 날짜

    @Column(name = "expense_report_status", length = 200)
    private String expenseReportStatus;     // 보고서상태

    @Column(name = "expense_approve")
    private String expenseApprove;      // 승인/반려

    @Column(name = "total_expense_cost")
    private int totalExpenseCost;               // 총금액

    @Column(name = "member_name")
    private String memberName;;             // 지점장명

    @Column(name = "branch_name")
    private String branchName;              // 지점명

    @Column(name = "branch_code", length = 200, nullable = true)
    private String branchCode;              // 지점코드

    public Expense() {

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

    public Expense repairCost(int repairCost) {
        this.repairCost = repairCost;
        return this;
    }

    public Expense expenseSubmissionDate(Date expenseSubmissionDate) {
        this.expenseSubmissionDate = expenseSubmissionDate;
        return this;
    }


    public Expense monthDate(String monthDate) {
        this.monthDate = monthDate;
        return this;
    }

    public Expense expenseReportStatus(String expenseReportStatus) {
        this.expenseReportStatus = expenseReportStatus;
        return this;
    }

    public Expense expenseApprove(String expenseApprove) {
        this.expenseApprove = expenseApprove;
        return this;
    }

    public Expense totalExpenseCost(int totalExpenseCost) {
        this.totalExpenseCost = totalExpenseCost;
        return this;
    }

    public Expense memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public Expense branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public Expense branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public Expense build(){
        return new Expense(
                expenseReportCode,
                electricityBill,
                waterBill,
                gasBill,
                partTimeSalary,
                repairCost,
                expenseSubmissionDate,
                monthDate,
                expenseReportStatus,
                expenseApprove,
                totalExpenseCost,
                memberName,
                branchName,
                branchCode
        );
    }

    public void setExpenseReportStatus(String expenseReportStatus) {
        this.expenseReportStatus = expenseReportStatus;
    }

    public void setExpenseApprove(String expenseApprove) {
        this.expenseApprove = expenseApprove;
    }
}
