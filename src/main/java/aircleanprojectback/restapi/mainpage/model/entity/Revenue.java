package aircleanprojectback.restapi.mainpage.model.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_revenue")
public class Revenue {

    @Id
    @Column(name = "revenue_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int revenuePk;

    @Column(name = "revenue_name")
    private String revenueName;

    @Column(name = "revenue_amount")
    private int revenueAmount;

    @Column(name = "revenue_date")
    private Date revenueDate;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "headquarters_code")
    private String headquartersCode;

    @Column(name = "revenue_status")
    private String revenueStatus;
}
