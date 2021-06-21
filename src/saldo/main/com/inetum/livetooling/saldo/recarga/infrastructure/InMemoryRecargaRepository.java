package com.inetum.livetooling.saldo.recarga.infrastructure;

import com.inetum.livetooling.saldo.recarga.domain.Recarga;
import com.inetum.livetooling.saldo.recarga.domain.RecargaRepository;
import com.inetum.livetooling.shared.domain.Service;

@Service
public class InMemoryRecargaRepository implements RecargaRepository {

    @Override
    public void save(Recarga recarga) {

    }
}
