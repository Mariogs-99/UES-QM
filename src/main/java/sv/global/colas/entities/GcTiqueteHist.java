/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_TIQUETE_HIST", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcTiqueteHist.findAll", query = "SELECT g FROM GcTiqueteHist g")})
public class GcTiqueteHist implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_TIQUETE_ID", nullable = false, precision = 22)
    private Long nTiqueteId;
    @Column(name = "N_RESERVA_CITA_ID")
    private BigInteger nReservaCitaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_PRIORIDAD_ID", nullable = false)
    private BigInteger nPrioridadId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_TRAMITE_ID", nullable = false)
    private BigInteger nTramiteId;
    @Column(name = "N_TIQUETE_REA")
    private BigInteger nTiqueteRea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "S_CORRELATIVO", nullable = false, length = 25)
    private String sCorrelativo;
    @Size(max = 14)
    @Column(name = "NIT", length = 14)
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "M_ESTADO", nullable = false)
    private BigInteger mEstado;
    @Column(name = "FH_LLEGADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhLlegada;
    @Column(name = "FH_LLAMADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhLlamado;
    @Column(name = "FHI_PROCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhiProceso;
    @Column(name = "FHF_PROCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhfProceso;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;

    public GcTiqueteHist() {
    }

    public GcTiqueteHist(Long nTiqueteId) {
        this.nTiqueteId = nTiqueteId;
    }

    public GcTiqueteHist(Long nTiqueteId, BigInteger nPrioridadId, BigInteger nTramiteId, String sCorrelativo, BigInteger mEstado) {
        this.nTiqueteId = nTiqueteId;
        this.nPrioridadId = nPrioridadId;
        this.nTramiteId = nTramiteId;
        this.sCorrelativo = sCorrelativo;
        this.mEstado = mEstado;
    }

    public Long getNTiqueteId() {
        return nTiqueteId;
    }

    public void setNTiqueteId(Long nTiqueteId) {
        this.nTiqueteId = nTiqueteId;
    }

    public BigInteger getNReservaCitaId() {
        return nReservaCitaId;
    }

    public void setNReservaCitaId(BigInteger nReservaCitaId) {
        this.nReservaCitaId = nReservaCitaId;
    }

    public BigInteger getNPrioridadId() {
        return nPrioridadId;
    }

    public void setNPrioridadId(BigInteger nPrioridadId) {
        this.nPrioridadId = nPrioridadId;
    }

    public BigInteger getNTramiteId() {
        return nTramiteId;
    }

    public void setNTramiteId(BigInteger nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public BigInteger getNTiqueteRea() {
        return nTiqueteRea;
    }

    public void setNTiqueteRea(BigInteger nTiqueteRea) {
        this.nTiqueteRea = nTiqueteRea;
    }

    public String getSCorrelativo() {
        return sCorrelativo;
    }

    public void setSCorrelativo(String sCorrelativo) {
        this.sCorrelativo = sCorrelativo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public BigInteger getMEstado() {
        return mEstado;
    }

    public void setMEstado(BigInteger mEstado) {
        this.mEstado = mEstado;
    }

    public Date getFhLlegada() {
        return fhLlegada;
    }

    public void setFhLlegada(Date fhLlegada) {
        this.fhLlegada = fhLlegada;
    }

    public Date getFhLlamado() {
        return fhLlamado;
    }

    public void setFhLlamado(Date fhLlamado) {
        this.fhLlamado = fhLlamado;
    }

    public Date getFhiProceso() {
        return fhiProceso;
    }

    public void setFhiProceso(Date fhiProceso) {
        this.fhiProceso = fhiProceso;
    }

    public Date getFhfProceso() {
        return fhfProceso;
    }

    public void setFhfProceso(Date fhfProceso) {
        this.fhfProceso = fhfProceso;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nTiqueteId != null ? nTiqueteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcTiqueteHist)) {
            return false;
        }
        GcTiqueteHist other = (GcTiqueteHist) object;
        if ((this.nTiqueteId == null && other.nTiqueteId != null) || (this.nTiqueteId != null && !this.nTiqueteId.equals(other.nTiqueteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcTiqueteHist[ nTiqueteId=" + nTiqueteId + " ]";
    }
    
}
