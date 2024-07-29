package aircleanprojectback;

import aircleanprojectback.restapi.water.service.ScheduleTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class FetchTest {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Test
    public void testFetchData(){

            scheduleTaskService.fetchData();

    }
}
