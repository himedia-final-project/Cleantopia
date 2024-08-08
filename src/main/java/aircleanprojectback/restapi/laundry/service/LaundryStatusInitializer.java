package aircleanprojectback.restapi.laundry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class LaundryStatusInitializer implements CommandLineRunner {

    private final ManagementService laundryService;

    @Autowired
    public LaundryStatusInitializer(ManagementService laundryService) {
        this.laundryService = laundryService;
    }

    @Override
    public void run(String... args) throws Exception {
        laundryService.updateLaundryArrivedStatus();
        laundryService.updateLaundryBringCustomerStatus();
    }


}
