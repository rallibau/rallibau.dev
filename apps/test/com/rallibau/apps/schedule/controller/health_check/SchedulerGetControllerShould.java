package com.rallibau.apps.schedule.controller.health_check;

import com.rallibau.apps.ApplicationTestCase;
import com.rallibau.apps.schedule.ScheduleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ScheduleApplication.class)
@SpringBootTest
public class SchedulerGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        assertResponse("/scheduler/health-check", 200, "{'application':'scheduler','status':'ok'}");
    }
}
