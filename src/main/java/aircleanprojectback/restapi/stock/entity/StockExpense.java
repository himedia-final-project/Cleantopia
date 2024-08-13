package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "tbl_stock_expense")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StockExpense {

    @Id
    @Column(name = "stock_expense_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockExpenseCode;

    @Column(name = "expense_date")
    private Date expenseDate;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "total_expense")
    private int totalExpense;

    @Column(name = "bleach_expense")
    private int bleachExpense;

    @Column(name = "detergent_expense")
    private int detergentExpense;

    @Column(name = "drum_cleaner_expense")
    private int drumCleanerExpense;

    @Column(name = "dry_cleaner_filter_expense")
    private int dryCleanerFilterExpense;

    @Column(name = "dryer_filter_expense")
    private int dryerFilterExpense;

    @Column(name = "laundry_filter_expense")
    private int laundryFilterExpense;

    @Column(name = "remover_expense")
    private int removerExpense;

    @Column(name = "sheet_expense")
    private int sheetExpense;

    @Column(name = "softner_expense")
    private int softnerExpense;

    public StockExpense expenseDate(Date var){
        expenseDate = var;
        return this;
    }

    public StockExpense branchCode(String var){
        branchCode = var;
        return this;
    }

    public StockExpense totalExpense(int var){
        totalExpense =var;
        return this;
    }
    public StockExpense bleachExpense(int var){
        bleachExpense =var;
        return this;
    }
    public StockExpense detergentExpense(int var){
        detergentExpense =var;
        return this;
    }
    public StockExpense drumCleanerExpense(int var){
        drumCleanerExpense =var;
        return this;
    }
    public StockExpense dryCleanerFilterExpense(int var){
        dryCleanerFilterExpense =var;
        return this;
    }
    public StockExpense dryerFilterExpense(int var){
        dryerFilterExpense =var;
        return this;
    }
    public StockExpense laundryFilterExpense(int var){
        laundryFilterExpense =var;
        return this;
    }
    public StockExpense removerExpense(int var){
        removerExpense =var;
        return this;
    }
    public StockExpense sheetExpense(int var){
        sheetExpense =var;
        return this;
    }
    public StockExpense softnerExpense(int var){
        softnerExpense =var;
        return this;
    }

}
