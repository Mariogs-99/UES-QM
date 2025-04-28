/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Alcides Nolasco
 */
@Entity
@Table(name = "GC_TRAMITE", catalog = "", schema = "")

public class GcTramite  extends PanacheEntityBase {
    private static final long serialVersionUID = 1L;
    @Id
  
    @Column(name = "N_TRAMITE_ID", nullable = false, precision = 22)
    private Long nTramiteId;

    @Column(name = "N_SERVICIOS_ID", nullable = false, precision = 22)
    private String NServiciosId;
 
    @Column(name = "N_PESO", nullable = false)
    private BigInteger nPeso;

    @Column(name = "N_ICONO")//, nullable = false)
    private int nIcono;
 
    @Column(name = "B_NIT_REQUERIDO", nullable = false)
    private short bNitRequerido;

    @Column(name = "B_ESCALAMIENTO", nullable = false)
    private short bEscalamiento;
   
    @Column(name = "D_TRAMITE", length = 256)
    private String dTramite;
    @Column(name = "B_RESERVA_CITA", nullable = false)
    private Integer bReservaCita;

    @Column(name = "C_USUARIO_CREA", nullable = false, length = 100)
    private String cUsuarioCrea;
 
    @Column(name = "C_USUARIO_MODI", nullable = false, length = 100)
    private String cUsuarioModi;

    @Column(name = "FI_VIGENCIA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiVigencia;
    @Column(name = "FF_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffVigencia;
    @Column(name = "F_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fModifica;

    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;

    @Column(name = "S_NOMBRE", nullable = false, length = 256)
    private String sNombre;
    @Column(name = "N_ORDEN")
    private Long nOrden;
    /*
    @JsonIgnoreProperties("gcTramiteList")
    @ManyToOne(optional = false)
    @JoinColumn(name = "n_Servicios_Id")
    private GcServicios nServiciosId;
     */
    public GcTramite() {
    }

    public GcTramite(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public GcTramite(Long nTramiteId, BigInteger nPeso ,short bNitRequerido, short bEscalamiento, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, short bActiva, String sNombre,String NServiciosId) {
        this.nTramiteId = nTramiteId;
        this.nPeso = nPeso;
        this.bNitRequerido = bNitRequerido;
        this.bEscalamiento = bEscalamiento;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.bActiva = bActiva;
        this.sNombre = sNombre;
        this.NServiciosId = NServiciosId;
    }

    public Long getNTramiteId() {
        return nTramiteId;
    }

    public void setNTramiteId(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public BigInteger getNPeso() {
        return nPeso;
    }

    public void setNPeso(BigInteger nPeso) {
        this.nPeso = nPeso;
    }
     public int getNIcono() {
        return nIcono;
    }

    public void setNIcono(int nIco) {
        this.nIcono = nIco;
    }
    public short getBNitRequerido() {
        return bNitRequerido;
    }

    public void setBNitRequerido(short bNitRequerido) {
        this.bNitRequerido = bNitRequerido;
    }

    public short getBEscalamiento() {
        return bEscalamiento;
    }

    public void setBEscalamiento(short bEscalamiento) {
        this.bEscalamiento = bEscalamiento;
    }

    public String getDTramite() {
        return dTramite;
    }

    public void setDTramite(String dTramite) {
        this.dTramite = dTramite;
    }

    public String getCUsuarioCrea() {
        return cUsuarioCrea;
    }

    public void setCUsuarioCrea(String cUsuarioCrea) {
        this.cUsuarioCrea = cUsuarioCrea;
    }

    public String getCUsuarioModi() {
        return cUsuarioModi;
    }

    public void setCUsuarioModi(String cUsuarioModi) {
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

    public Date getFModifica() {
        return fModifica;
    }

    public void setFModifica(Date fModifica) {
        this.fModifica = fModifica;
    }

    public short getBActiva() {
        return bActiva;
    }

    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getSNombre() {
        return sNombre;
    }

    public void setSNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public Long getNOrden() {
        return nOrden;
    }

    public void setNOrden(Long nOrden) {
        this.nOrden = nOrden;
    }

   
    public String getNServiciosId() {
        return NServiciosId;
    }

    public void setNServiciosId(String nServiciosId) {
        this.NServiciosId = nServiciosId;
    }
   


}
