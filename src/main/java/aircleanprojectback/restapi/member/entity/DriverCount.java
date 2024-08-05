package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_driver_region_count")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DriverCount {


    @Id
    @Column(name = "driver_region")
    private String driverRegion;

    @Column(name = "region_count")
    private int regionCount;
}
