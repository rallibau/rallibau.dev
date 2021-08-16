package com.rallibau.saldo;

import com.rallibau.apps.gestionSaldo.GestionSaldoApplication;
import com.rallibau.shared.infraestructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GestionSaldoApplication.class)
@SpringBootTest
public abstract class ClientesContextInfraestructureTestCase  extends InfrastructureTestCase {
}
