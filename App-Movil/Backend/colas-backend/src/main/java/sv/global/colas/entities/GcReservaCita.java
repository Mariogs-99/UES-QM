/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_RESERVA_CITA", catalog = "", schema = "")

public class GcReservaCita extends PanacheEntityBase {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "N_RESERVA_CITA_ID")
    private Long nReservaCitaId;
    @Column(name = "C_UNIDAD_RECEP", unique = true, nullable = false)
    private String cunidadRecep;
    @Column(name = "FH_RESERVACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhReservacion;
    @Column(name = "S_CORREO", length = 256)
    private String sCorreo;
   
    @Column(name = "NIT", length = 14)
    private String nit;

    @Column(name = "N_TELEFONO", length = 25)
    private String nTelefono;
 
    @Column(name = "S_OBSERVACIONES", length = 1024)
    private String sObservaciones;

    @Column(name = "S_COD_VERIFICA", length = 50)
    private String sCodVerifica;
    @Column(name = "B_ESTADO")
    private Integer bEstado;
    
     
    @Column(name = "N_TRAMITE_ID", nullable = false, precision = 22)
    private Long nTramiteId;
    


    public GcReservaCita() {
    }

    public GcReservaCita(Long nReservaCitaId) {
        this.nReservaCitaId = nReservaCitaId;
    }

    public GcReservaCita(Long nReservaCitaId, Date fhReservacion, String sCodVerifica) {
        this.nReservaCitaId = nReservaCitaId;
        this.fhReservacion = fhReservacion;
        this.sCodVerifica = sCodVerifica;
    }

    public Long getNReservaCitaId() {
        return nReservaCitaId;
    }

    public void setNReservaCitaId(Long nReservaCitaId) {
        this.nReservaCitaId = nReservaCitaId;
    }

 

    public void setCunidadRecep(String cunidadRecep) {
        this.cunidadRecep = cunidadRecep;
    }

    public void setnTramiteId(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public String getCunidadRecep() {
        return cunidadRecep;
    }

    public Long getnTramiteId() {
        return nTramiteId;
    }

    public Date getFhReservacion() {
        return fhReservacion;
    }

    public void setFhReservacion(Date fhReservacion) {
        this.fhReservacion = fhReservacion;
    }

    public String getSCorreo() {
        return sCorreo;
    }

    public void setSCorreo(String sCorreo) {
        this.sCorreo = sCorreo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNTelefono() {
        return nTelefono;
    }

    public void setNTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }

    public String getSObservaciones() {
        return sObservaciones;
    }

    public void setSObservaciones(String sObservaciones) {
        this.sObservaciones = sObservaciones;
    }


    
    public String getsCodVerifica() {
		return sCodVerifica;
	}

    public void setsCodVerifica(String sCodVerifica) {
            this.sCodVerifica = sCodVerifica;
    }

    public Integer getbEstado() {
        return bEstado;
    }

    public void setbEstado(Integer bEstado) {
        this.bEstado = bEstado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nReservaCitaId != null ? nReservaCitaId.hashCode() : 0);
        return hash;
    }


}
