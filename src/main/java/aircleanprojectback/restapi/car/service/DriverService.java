package aircleanprojectback.restapi.car.service;

import aircleanprojectback.restapi.car.entity.Driver;
import aircleanprojectback.restapi.car.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public boolean isDriverAssigned(String driverName) {
        Optional<Driver> driver = driverRepository.findById(driverName);
        return driver.isPresent();
    }

    // 추가적인 메서드들 필요 시 추가
}
