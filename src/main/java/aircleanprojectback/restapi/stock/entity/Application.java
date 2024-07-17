package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_application")
@AllArgsConstructor
@Getter
@ToString
public class Application {

    @Id
    @Column(name = "application_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationCode;

    @Column(name = "application_count", nullable = false)
    private int applicationCount;

    @Column(name = "applicant_name", length =  50, nullable = false)
    private String applicantName;

    @Column(name = "approver_name", length = 50)
    private String approverName;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

//    @Column(name = "")
//    private String laundrySupplyCode;
//    private String laundryPartCode;
//    private String approvalStatus;
//    private String applicantIsManager;

    public Application() {}



}
