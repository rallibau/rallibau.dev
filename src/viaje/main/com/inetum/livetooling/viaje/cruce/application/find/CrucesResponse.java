package com.inetum.livetooling.viaje.cruce.application.find;

import com.inetum.livetooling.shared.domain.bus.query.Response;

import java.util.List;

public class CrucesResponse implements Response {
    private final List<CruceResponse> cruces;

    public List<CruceResponse> getCruces() {
        return cruces;
    }

    public CrucesResponse(List<CruceResponse> cruces) {
        this.cruces = cruces;
    }

    public void addCruce(CruceResponse cruce) {
        this.cruces.add(cruce);
    }

    public static class CruceResponse{
        private final String id;
        private final String ruta;
        private final String estado;
        private final String tagNumero;


        public CruceResponse(String id, String ruta, String estado, String tagNumero) {
            this.id = id;
            this.ruta = ruta;
            this.estado = estado;
            this.tagNumero = tagNumero;
        }


        public String getId() {
            return id;
        }

        public String getRuta() {
            return ruta;
        }

        public String getEstado() {
            return estado;
        }

        public String getTagNumero() {
            return tagNumero;
        }
    }
}
