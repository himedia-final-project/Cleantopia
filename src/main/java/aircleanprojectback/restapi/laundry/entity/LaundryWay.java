package aircleanprojectback.restapi.laundry.entity;

import aircleanprojectback.restapi.laundry.entity.Laundry;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_laundry_way")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class LaundryWay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laundry_way_id")
    private int laundryWayId;

    @ManyToOne
    @JoinColumn(name = "laundry_code", referencedColumnName = "laundry_code")
    private Laundry laundry; // Laundry 엔티티를 참조


    @Column(name = "laundry_time")
    private String laundryTime;

    // 세제량
    @Column(name = "laundry_detergent_amount")
    private String laundryDetergentAmount;

    // 세탁용 물량
    @Column(name = "laundry_water_amount")
    private String laundryWaterAmount;

    // 건조 시간
    @Column(name = "laundry_drying_time")
    private String laundryDryingTime;

    // 드라이클리닝 시간
    @Column(name = "laundry_dry_cleaning_time")
    private String laundryDryCleaningTime;
}
