package com.inetum.livetooling.saldo.application.recargasManuales;

import com.inetum.livetooling.saldo.recarga.domain.Recarga;
import com.inetum.livetooling.saldo.recarga.domain.RecargaId;
import com.inetum.livetooling.saldo.recarga.domain.RecargaRepository;
import com.inetum.livetooling.shared.domain.Service;

@Service
public class InsercionRecarga {
    private final RecargaRepository recargaRepository;

    public InsercionRecarga(RecargaRepository recargaRepository) {
        this.recargaRepository = recargaRepository;
    }

    public void create(RecargaId id) {
        Recarga recarga = Recarga.create(id);
        recargaRepository.save(recarga);
        recarga.pullDomainEvents();
    }
}
