package aircleanprojectback.restapi.laundry.entity;

import aircleanprojectback.restapi.water.entity.WaterSupply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_water_tank")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WaterTank {

    @Id
    @Column(name = "water_tank_no")
    private int waterTankNo;

    @Column(name = "water_max_capacity")
    private int waterMaxCapacity;

    @Column(name = "water_cur_capacity")
    private int waterCurCapacity;

    @Column(name = "branch_code")
    private String branchCode;


    // Add constructors, getters, and setters
}
