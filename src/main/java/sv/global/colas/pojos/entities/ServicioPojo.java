/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import sv.global.colas.entities.GcServicios;
import sv.global.colas.entities.GcTramite;

/**
 *
 * @author Owner
 */
public class ServicioPojo implements Serializable {
    private Long nServiciosId;
    private String sNombre;
    private String dServicios;
    private String cUsuarioCrea;
    private String cUsuarioModi;
    private Date fiVigencia;
    private Date ffVigencia;
    private Date fModifica;
    private BigInteger nOrden;
    private short bActiva;
    private List<GcTramite> gcTramiteList;
    private List<GcServicios> gcServiciosList;
    private Long orden;

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }
    
    public Long getnServiciosId() {
        return nServiciosId;
    }

    public void setnServiciosId(Long nServiciosId) {
        this.nServiciosId = nServiciosId;
    }

    public String getdServicios() {
        return dServicios;
    }

    public void setdServicios(String dServicios) {
        this.dServicios = dServicios;
    }

    public String getcUsuarioCrea() {
        return cUsuarioCrea;
    }

    public void setcUsuarioCrea(String cUsuarioCrea) {
        this.cUsuarioCrea = cUsuarioCrea;
    }

    public String getcUsuarioModi() {
        return cUsuarioModi;
    }

    public void setcUsuarioModi(String cUsuarioModi) {
        this.cUsuarioModi = cUsuarioModi;
    }

    public Date getFiVigencia() {
        return fiVigencia;
    }

    public void setFiVigencia(Date fiVigencia) {
        this.fiVigencia = fiVigencia;
    }

    public Date getFfVigencia() {
        return ffVigencia;
    }

    public void setFfVigencia(Date ffVigencia) {
        this.ffVigencia = ffVigencia;
    }

    public Date getfModifica() {
        return fModifica;
    }

    public void setfModifica(Date fModifica) {
        this.fModifica = fModifica;
    }

    public BigInteger getnOrden() {
        return nOrden;
    }

    public void setnOrden(BigInteger nOrden) {
        this.nOrden = nOrden;
    }

    public short getbActiva() {
        return bActiva;
    }

    public void setbActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public List<GcTramite> getGcTramiteList() {
        return gcTramiteList;
    }

    public void setGcTramiteList(List<GcTramite> gcTramiteList) {
        this.gcTramiteList = gcTramiteList;
    }

    public List<GcServicios> getGcServiciosList() {
        return gcServiciosList;
    }

    public void setGcServiciosList(List<GcServicios> gcServiciosList) {
        this.gcServiciosList = gcServiciosList;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }
    
}
