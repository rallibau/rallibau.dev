package com.rallibau.apps.bpm.controller.health_check;

import com.rallibau.apps.ApplicationTestCase;
import com.rallibau.apps.monolit.MonolitApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = MonolitApplication.class)
@SpringBootTest
public class SchedulerBpmHealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        assertResponse("/bpm/health-check", 200, "{'application':'bpm','status':'ok'}");
    }
}
