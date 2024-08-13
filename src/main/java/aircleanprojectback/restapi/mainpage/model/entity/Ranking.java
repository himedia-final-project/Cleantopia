package aircleanprojectback.restapi.mainpage.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_rank")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Ranking {

    @Id
    @Column(name = "rank_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankCode;

    @Column(name = "rank_index")
    private int rankIndex;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_image")
    private String memberImage;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "total_revenue")
    private long totalRevenue;

    public void setRankIndex(int i) {
        this.setRankIndex(i);
    }
}
