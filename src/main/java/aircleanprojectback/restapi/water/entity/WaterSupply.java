package aircleanprojectback.restapi.water.entity;

import aircleanprojectback.restapi.laundry.entity.WaterTank;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_water_supply")
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "water_supply_code")
    private int waterSupplyCode;

    @Column(name = "msr_date")
    private String msrDate;

    @Column(name = "site_id")
    private String siteId;

    @Column(name = "w_temp")
    private String wTemp;

    @Column(name = "w_ph")
    private String wPh;

    @Column(name = "w_do")
    private String wDo;

    @Column(name = "w_tn")
    private String wTn;

    @Column(name = "w_tp")
    private String wTp;

    @Column(name = "w_cn")
    private String wCn;

    @Column(name = "w_phen")
    private String wPhen;

    @Column(name = "water_volume")
    private String waterVolume;

    @Column(name = "water_tank_no")
    private String waterTankNo;


    // Add constructors, getters, and setters
}
