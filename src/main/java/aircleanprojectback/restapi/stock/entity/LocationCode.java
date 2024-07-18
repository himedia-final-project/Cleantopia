package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_branch")
@AllArgsConstructor
@Getter
@ToString
public class LocationCode {

    @Id
    @Column(name = "branch_code", length = 100, nullable = false)
    private String branchCode;

    @Column(name = "branch_name", length = 100, nullable = false)
    private String branchName;

    protected LocationCode() {}

    public LocationCode branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public LocationCode branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }
}
