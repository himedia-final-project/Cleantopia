package aircleanprojectback.restapi.car.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "tbl_branch")
public class Branch {

    @Id
    @Column(name = "branch_code", nullable = false, length = 100)
    private String branchCode; // 지점코드

    @Column(name = "branch_name", nullable = false, length = 100)
    private String branchName; // 지점 이름

    @Column(name = "branch_region", nullable = false, length = 100)
    private String branchRegion; // 지점 지역

    @Column(name = "branch_image", nullable = false, length = 500)
    private String branchImage; // 지점 사진

    public Branch() {}

    public Branch branchCode(String branchCode) {
        this.branchCode = branchCode;
        return this;
    }

    public Branch branchName(String branchName) {
        this.branchName = branchName;
        return this;
    }

    public Branch branchRegion(String branchRegion) {
        this.branchRegion = branchRegion;
        return this;
    }

    public Branch branchImage(String branchImage) {
        this.branchImage = branchImage;
        return this;
    }

    public Branch build() {return new Branch(branchCode,branchName,branchRegion,branchImage);}
}
