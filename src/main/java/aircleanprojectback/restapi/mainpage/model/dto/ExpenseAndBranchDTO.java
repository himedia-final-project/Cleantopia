package aircleanprojectback.restapi.mainpage.model.dto;

import aircleanprojectback.restapi.member.dto.BranchDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExpenseAndBranchDTO {

    private int expenseReportCode;          // 지점지출보고서코드
    private int electricityBill;            // 전기세
    private  int waterBill;                 // 수도세
    private int gasBill;                    // 가스비
    private  int partTimeSalary;            // 알바비
    private  int repairCost;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")// 시설물수리비
    private Date expenseSubmissionDate;     // 제출일
    private String monthDate;               // 이번달 지출일
    private String expenseReportStatus;     // 보고서상태
    private String expenseApprove;          // 승인/반려
    private int totalExpenseCost;           // 총지출금액
    private String memberName;              // 지점장
    private String branchName;              // 지점명
    private BranchDTO branch;              // 지점코드
    private String expenseRemark;           // 비고
}
