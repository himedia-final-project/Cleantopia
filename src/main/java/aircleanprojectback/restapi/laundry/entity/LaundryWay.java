package aircleanprojectback.restapi.laundry.entity;

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

    @OneToOne
    @JoinColumn(name = "laundry_code", referencedColumnName = "laundry_code")
    private Laundry laundry;

    // 세탁시간 int로
    @Column(name = "laundry_time")
    private int laundryTime;

    // 픽업상태
    @Column(name = "laundry_pick_up_status")
    private String laundryPickUpStatus;

    // 세제량
    @Column(name = "laundry_detergent_amount")
    private int laundryDetergentAmount;

    // 세탁용 물량
    @Column(name = "laundry_water_amount")
    private int laundryWaterAmount;

    // 건조 시간
    @Column(name = "laundry_drying_time")
    private int laundryDryingTime;

    // 드라이클리닝 시간
    @Column(name = "laundry_dry_cleaning_time")
    private int laundryDryCleaningTime;
}
