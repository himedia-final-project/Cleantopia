package aircleanprojectback.restapi.report.repository;


import aircleanprojectback.restapi.report.entity.VehicleRepair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepairRepository extends JpaRepository<VehicleRepair, String> {
}

