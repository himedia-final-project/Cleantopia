package aircleanprojectback.restapi.mainpage.model.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_expenditure")
public class Expenditure {

    @Id
    @Column(name = "expenditure_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenditureCode;

    @Column(name = "expenditure_name")
    private String expenditureName;

    @Column(name = "expenditure_cost")
    private int expenditureCost;

    @Column(name = "expenditure_date")
    private Date expenditureDate;

    @Column(name = "headquarters_code")
    private String headquartersCode;

    @Column(name = "branch_code")
    private String branchCode;

}
