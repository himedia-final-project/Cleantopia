package aircleanprojectback.restapi.report.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    // 지출보고서

    private int expenseReportCode;          // 지점지출보고서코드
    private int electricityBill;            // 전기세
    private  int waterBill;                 // 수도세
    private int gasBill;                    // 가스비
    private  int partTimeSalary;            // 알바비
    private  int repairCost;                // 알바비
    private Date expenseSubmissionDate;     // 제출일
    private String expenseReportStatus;     // 보고서상태
    private int totalExpenseCost;           // 총지출금액
    private String memberName;              // 지점장
    private String branchName;              // 지점명
    private String branchCode;              // 지점코드
}
