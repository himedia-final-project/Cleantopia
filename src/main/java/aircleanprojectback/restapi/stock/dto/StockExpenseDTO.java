package aircleanprojectback.restapi.stock.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockExpenseDTO {
    private int stockExpenseCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date expenseDate;
    private String branchCode;
    private int totalExpense;
    private int bleachExpense;
    private int detergentExpense;
    private int drumCleanerExpense;
    private int dryCleanerFilterExpense;
    private int dryerFilterExpense;

    private int laundryFilterExpense;


    private int removerExpense;


    private int sheetExpense;


    private int softnerExpense;
}
