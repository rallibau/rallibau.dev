package com.inetum.livetooling.apps.gestionViaje.controller.health_check;

import com.inetum.livetooling.apps.ApplicationTestCase;
import org.junit.jupiter.api.Test;

public class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        assertResponse("/viaje/health-check", 200, "{'application':'gestionViaje','status':'ok'}");
    }
}
