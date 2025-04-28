/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.util.List;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.TbUnidadRecep;

/**
 *
 * @author Owner
 */
public class ConfLlamadoPojo {
    private GcConfLlamado gcconfLlamadoEntidad;
    private List<TbUnidadRecep> cUnidadRecepList;
    private String dConfLlamado;
    private String unidadRecep;
    private String unidadRecepId;

    public String getUnidadRecepId() {
        return unidadRecepId;
    }

    public void setUnidadRecepId(String unidadRecepId) {
        this.unidadRecepId = unidadRecepId;
    }

    public String getUnidadRecep() {
        return unidadRecep;
    }

    public void setUnidadRecep(String unidadRecep) {
        this.unidadRecep = unidadRecep;
    }

    public String getdConfLlamado() {
        return dConfLlamado;
    }

    public void setdConfLlamado(String dConfLlamado) {
        this.dConfLlamado = dConfLlamado;
    }

    public GcConfLlamado getGcconfLlamadoEntidad() {
        return gcconfLlamadoEntidad;
    }

    public void setGcconfLlamadoEntidad(GcConfLlamado gcconfLlamadoEntidad) {
        this.gcconfLlamadoEntidad = gcconfLlamadoEntidad;
    }

    public List<TbUnidadRecep> getcUnidadRecepList() {
        return cUnidadRecepList;
    }

    public void setcUnidadRecepList(List<TbUnidadRecep> cUnidadRecepList) {
        this.cUnidadRecepList = cUnidadRecepList;
    }

}
