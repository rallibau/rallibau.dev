package com.inetum.livetooling.saldo;

import com.inetum.livetooling.apps.gestionSaldo.GestionSaldoApplication;
import com.inetum.livetooling.shared.infraestructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GestionSaldoApplication.class)
@SpringBootTest
public abstract class ClientesContextInfraestructureTestCase  extends InfrastructureTestCase {
}
