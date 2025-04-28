/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.io.Serializable;

/**
 *
 * @author Owner
 */
public class GcJerarquiaSeccionPojo implements Serializable {
    private String unidad;
    private Long jerarquia;
    private String usuario;
    private String descripcion;
    private Long seccion;
    private Long jrqsecId;
    private Long escritorio;

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(Long jerarquia) {
        this.jerarquia = jerarquia;
    }

    public Long getSeccion() {
        return seccion;
    }

    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }
    
    public Long getJrqsecId() {
        return jrqsecId;
    }

    public void setJrqsecId(Long jrqsecId) {
        this.jrqsecId = jrqsecId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getEscritorio() {
        return escritorio;
    }

    public void setEscritorio(Long escritorio) {
        this.escritorio = escritorio;
    }
    
}
