package aircleanprojectback.restapi.report.repository;


import aircleanprojectback.restapi.report.entity.VehicleRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepairRepository extends JpaRepository<VehicleRepair, Integer> {

//    @Query(name = "select * from tbl_vehicle_repair_report where MONTH(vehicle_submission_date)=?1 and YEAR(vehicle_submission_date)=?2 and vehicle_report_status =?3",nativeQuery = true)
//    List<VehicleRepair> findAllForDate(int monthNumber, String year, String y);

//    @Query(name = "select * from tbl_vehicle_repair_report where YEAR(vehicle_submission_date)=?1 and vehicle_report_status =?2",nativeQuery = true)
//    List<VehicleRepair> findAllForYear(String year, String y);

    @Query(value = "select * from tbl_vehicle_repair_report where MONTH(vehicle_submission_date)=?1 and YEAR(vehicle_submission_date)=?2 and vehicle_report_status =?3",nativeQuery = true)
    List<VehicleRepair> getDate(int monthNumber, String year, String y);

    @Query(value = "select * from tbl_vehicle_repair_report where YEAR(vehicle_submission_date)=?1 and vehicle_report_status =?2",nativeQuery = true)
    List<VehicleRepair> getYear(String year, String y);
}