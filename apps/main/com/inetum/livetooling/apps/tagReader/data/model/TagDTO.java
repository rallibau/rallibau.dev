package com.inetum.livetooling.apps.tagReader.data.model;

import java.io.Serializable;

/**
 * POJO for TAG objects
 */

public class TagDTO implements Serializable {

    private String numeroTAG;
    private int operadorTAG;
    private int tipoTAG;
    private String categoriaTAG;
    private int status;
    private String horaActualizacion;
    private String fechaActualizacion;
    private Long importeSaldo;

    public TagDTO() {

    }

    public TagDTO(String numeroTAG, int operadorTAG, int tipoTAG, String categoriaTAG, int status, String horaActualizacion, String fechaActualizacion, Long importeSaldo) {
        this.numeroTAG = numeroTAG;
        this.operadorTAG = operadorTAG;
        this.tipoTAG = tipoTAG;
        this.categoriaTAG = categoriaTAG;
        this.status = status;
        this.horaActualizacion = horaActualizacion;
        this.fechaActualizacion = fechaActualizacion;
        this.importeSaldo = importeSaldo;
    }

    public String getNumeroTAG() {
        return numeroTAG;
    }

    public void setNumeroTAG(String numeroTAG) {
        this.numeroTAG = numeroTAG;
    }

    public int getOperadorTAG() {
        return operadorTAG;
    }

    public void setOperadorTAG(int operadorTAG) {
        this.operadorTAG = operadorTAG;
    }

    public int getTipoTAG() {
        return tipoTAG;
    }

    public void setTipoTAG(int tipoTAG) {
        this.tipoTAG = tipoTAG;
    }

    public String getCategoriaTAG() {
        return categoriaTAG;
    }

    public void setCategoriaTAG(String categoriaTAG) {
        this.categoriaTAG = categoriaTAG;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHoraActualizacion() {
        return horaActualizacion;
    }

    public void setHoraActualizacion(String horaActualizacion) {
        this.horaActualizacion = horaActualizacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getImporteSaldo() {
        return importeSaldo;
    }

    public void setImporteSaldo(Long importeSaldo) {
        this.importeSaldo = importeSaldo;
    }
}
