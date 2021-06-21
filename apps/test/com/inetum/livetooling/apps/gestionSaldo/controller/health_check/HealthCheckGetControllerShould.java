package com.inetum.livetooling.apps.gestionSaldo.controller.health_check;

import com.inetum.livetooling.apps.ApplicationTestCase;
import org.junit.jupiter.api.Test;

public class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        assertResponse("/saldo/health-check", 200, "{'application':'gestionSaldo','status':'ok'}");
    }
}
