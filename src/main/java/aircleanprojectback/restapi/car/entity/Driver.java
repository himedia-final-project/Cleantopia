package aircleanprojectback.restapi.car.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "tbl_driver")
public class Driver {

    @Id
    @Column(name = "driver_name", nullable = false, length = 100)
    private String driverName; // 운전자 이름

    public Driver() {}

    public Driver driverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public Driver build() { return new Driver(driverName); }
}
