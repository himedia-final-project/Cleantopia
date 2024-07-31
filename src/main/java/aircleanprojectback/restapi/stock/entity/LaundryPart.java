package aircleanprojectback.restapi.stock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_laundry_part")
@AllArgsConstructor
@Getter
@ToString
public class LaundryPart {

    @Id
    @Column(name = "laundry_part_code", length = 100, nullable = false)
    private String laundryPartCode;

    @Column(name = "laundry_part_name", length = 100, nullable = false)
    private String laundryPartName;

    @Column(name = "laundry_part_cost")
    private int laundryPartCost;

    public LaundryPart() {}
}