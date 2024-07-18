package aircleanprojectback.restapi.mainpage.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_total_revenue")
public class TotalRevenue {

    @Id
    @Column(name = "total_revenue_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int totalRevenueCode;

    @Column(name = "month_revenue")
    private int monthRevenue;

    @Column(name = "annual_revenue")
    private int annualRevenue;

    @Column(name = "headquarters_code")
    private String headquartersCode;

    @Column(name = "branch_code")
    private String branchCode;


}
