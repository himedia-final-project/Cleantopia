package aircleanprojectback.restapi.mainpage.model.entity;

import aircleanprojectback.restapi.member.entity.Branch;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(name = "tbl_expense_report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ExpenseAndBranch {

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

    @ManyToOne
    @JoinColumn(name = "branch_code")   // 지점명
    private Branch branch;

    @Column(name = "expense_remark")
    private String expenseRemark;
}
