package aircleanprojectback.restapi.water.entity;

import aircleanprojectback.restapi.laundry.entity.WaterTank;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_water_supply")
public class WaterSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "water_supply_code")
    private int waterSupplyCode;

    @Column(name = "water_temp")
    private String waterTemp;

    @Column(name = "ph")
    private String ph;

    @Column(name = "water_oxy")
    private String waterOxy;

    @Column(name = "nitrogen")
    private String nitrogen;

    @Column(name = "phosphorus")
    private String phosphorus;

    @Column(name = "organic_carbon")
    private String organicCarbon;

    @Column(name = "phenol")
    private String phenol;

    @Column(name = "cyan")
    private String cyan;

    @Column(name = "water_supply_date")
    private String waterSupplyDate;

    @ManyToOne
    @JoinColumn(name = "water_tank_no", referencedColumnName = "water_tank_no")
    private WaterTank waterTank;

    // Getters and setters

    public WaterSupply() {}

    // Add constructors, getters, and setters
}
