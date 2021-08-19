package com.rallibau.apps.bpm.controller.health_check;

import com.rallibau.apps.ApplicationTestCase;
import org.junit.jupiter.api.Test;

public class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        assertResponse("/bpm/health-check", 200, "{'application':'bpm','status':'ok'}");
    }
}
