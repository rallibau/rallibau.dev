package com.rallibau.apps.bpm.controller.health_check;

import com.rallibau.apps.ApplicationTestCase;
import com.rallibau.apps.bpm.BpmApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class BpmGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        assertResponse("/bpm/health-check", 200, "{'application':'bpm','status':'ok'}");
    }
}
