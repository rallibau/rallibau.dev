package com.inetum.livetooling.viaje.cruce.domain.rules;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.viaje.cruce.application.find.CruceFinder;
import com.inetum.livetooling.viaje.cruce.application.find.CrucesResponse;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.cruce.domain.rules.common.ErrorRegla;
import com.inetum.livetooling.viaje.cruce.domain.rules.common.ReglasInterface;
import com.inetum.livetooling.viaje.tag.application.find.TagViajeFinder;
import com.inetum.livetooling.viaje.tag.domain.TagViajesNumero;

import java.util.Optional;

@Service
public class TagShouldExsist implements ReglasInterface {

    private final CruceFinder cruceFinder;
    private final TagViajeFinder tagViajeFinder;



    public TagShouldExsist(CruceFinder cruceFinder, TagViajeFinder tagViajeFinder) {
        this.cruceFinder = cruceFinder;
        this.tagViajeFinder = tagViajeFinder;
    }

    @Override
    public void ejecutarRegla(String cruceId) throws ErrorRegla {
        Optional<CrucesResponse.CruceResponse> cruce = cruceFinder.get(new CruceId(cruceId));
        if (!cruce.isPresent()) {
            throw new ErrorRegla("El viaje no existe");
        }
        if (cruce.get().getTagNumero() == null || cruce.get().getTagNumero().isEmpty()) {
            throw new ErrorRegla("El viaje debe tener un  tag");
        }

        if(tagViajeFinder.get(new TagViajesNumero(cruce.get().getTagNumero())).isEmpty()) {
            throw new ErrorRegla("El tag no existe");
        }




    }
}
