/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.util.List;
import sv.global.colas.entities.GcConfTramite;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.TbUnidadRecep;

/**
 *
 * @author Owner
 */
public class ReasignacionPojo {
    private GcConfTramite GcConfTramite;
    private int general;
    private List<TbUnidadRecep> unidadesList;
    private List<GcTramite> tramitesList;
    private String unidadRecep;

    public List<TbUnidadRecep> getUnidadesList() {
        return unidadesList;
    }

    public void setUnidadesList(List<TbUnidadRecep> unidadesList) {
        this.unidadesList = unidadesList;
    }


    public List<GcTramite> getTramitesList() {
        return tramitesList;
    }

    public void setTramitesList(List<GcTramite> tramitesList) {
        this.tramitesList = tramitesList;
    }
    

    public GcConfTramite getGcConfTramite() {
        return GcConfTramite;
    }

    public void setGcConfTramite(GcConfTramite GcConfTramite) {
        this.GcConfTramite = GcConfTramite;
    }

    public int getGeneral() {
        return general;
    }

    public void setGeneral(int general) {
        this.general = general;
    }

    public String getUnidadRecep() {
        return unidadRecep;
    }

    public void setUnidadRecep(String unidadRecep) {
        this.unidadRecep = unidadRecep;
    }
}
