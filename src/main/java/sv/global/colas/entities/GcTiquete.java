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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@Table(name = "GC_TIQUETE", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcTiquete.findAll", query = "SELECT g FROM GcTiquete g")})
public class GcTiquete implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "N_TIQUETE_ID", nullable = false, precision = 22)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeTiquete")
    //@SequenceGenerator(name = "SeqGeTiquete", sequenceName = "SEQ_GC_TIQUETE")
    private Long nTiqueteId;
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Column(name = "N_TIQUETE_REA")
    private Integer nTiqueteRea;
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
    private Integer mEstado;
    @Column(name = "N_JRQ_SEC_ID", nullable = false)
    private Integer nJrqSecId;
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
    @Size(max = 256)
    @Column(name = "C_USUARIO_ATENDIO", length = 256)
    private String cUsuarioAtendio;
    @Size(max = 256)
    @Column(name = "C_USUARIO_CREA", length = 256)
    private String cUsuarioCrea;
    @Column(name = "N_TIEMPO_HOLGURA")
    private Integer nTiempoHolgura;
    @JoinColumn(name = "N_TRAMITE_ID", referencedColumnName = "N_TRAMITE_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcTramite nTramiteId;
    @JoinColumn(name = "N_RESERVA_CITA_ID", referencedColumnName = "N_RESERVA_CITA_ID")
    @ManyToOne
    private GcReservaCita nReservaCitaId;
    @JoinColumn(name = "N_PRIORIDAD_ID", referencedColumnName = "N_PRIORIDAD_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcPrioridad nPrioridadId;

    public GcTiquete() {
    }

    public GcTiquete(Long nTiqueteId) {
        this.nTiqueteId = nTiqueteId;
    }

    public GcTiquete(Long nTiqueteId, String sCorrelativo, Integer mEstado, String cUsuarioAtendio, String cUsuarioCrea) {
        this.nTiqueteId = nTiqueteId;
        this.sCorrelativo = sCorrelativo;
        this.mEstado = mEstado;
        this.cUsuarioAtendio = cUsuarioAtendio;
        this.cUsuarioCrea = cUsuarioCrea;
    }

    public Long getNTiqueteId() {
        return nTiqueteId;
    }

    public void setNTiqueteId(Long nTiqueteId) {
        this.nTiqueteId = nTiqueteId;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public Integer getNTiqueteRea() {
        return nTiqueteRea;
    }

    public void setNTiqueteRea(Integer nTiqueteRea) {
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

    public Integer getMEstado() {
        return mEstado;
    }

    public void setMEstado(Integer mEstado) {
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

    public GcTramite getNTramiteId() {
        return nTramiteId;
    }

    public void setNTramiteId(GcTramite nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public GcReservaCita getNReservaCitaId() {
        return nReservaCitaId;
    }

    public void setNReservaCitaId(GcReservaCita nReservaCitaId) {
        this.nReservaCitaId = nReservaCitaId;
    }

    public GcPrioridad getNPrioridadId() {
        return nPrioridadId;
    }

    public void setNPrioridadId(GcPrioridad nPrioridadId) {
        this.nPrioridadId = nPrioridadId;
    }
    
    public String getcUsuarioAtendio() {
		return cUsuarioAtendio;
	}

    public void setcUsuarioAtendio(String cUsuarioAtendio) {
            this.cUsuarioAtendio = cUsuarioAtendio;
    }

    public String getcUsuarioCrea() {
            return cUsuarioCrea;
    }

    public void setcUsuarioCrea(String cUsuarioCrea) {
            this.cUsuarioCrea = cUsuarioCrea;
    }

    public Integer getnTiempoHolgura() {
        return nTiempoHolgura;
    }

    public void setnTiempoHolgura(Integer nTiempoHolgura) {
        this.nTiempoHolgura = nTiempoHolgura;
    }
    
    public Integer getnJrqSecId() {
        return nJrqSecId;
    }

    public void setnJrqSecId(Integer nJrqSecId) {
        this.nJrqSecId = nJrqSecId;
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
        if (!(object instanceof GcTiquete)) {
            return false;
        }
        GcTiquete other = (GcTiquete) object;
        if ((this.nTiqueteId == null && other.nTiqueteId != null) || (this.nTiqueteId != null && !this.nTiqueteId.equals(other.nTiqueteId))) {
            return false;
        }
        return true;
    }
//
//    @Override
//    public String toString() {
//        return "sv.gob.mh.dgii.colas.entities.GcTiquete[ nTiqueteId=" + nTiqueteId + " ]";
//    }

    @Override
    public String toString() {
        return "GcTiquete{" + "nTiqueteId=" + nTiqueteId + ", cUnidadRecep=" + cUnidadRecep + ", nTiqueteRea=" + nTiqueteRea + ", sCorrelativo=" + sCorrelativo + ", nit=" + nit + ", mEstado=" + mEstado + ", nJrqSecId=" + nJrqSecId + ", fhLlegada=" + fhLlegada + ", fhLlamado=" + fhLlamado + ", fhiProceso=" + fhiProceso + ", fhfProceso=" + fhfProceso + ", cUsuarioAtendio=" + cUsuarioAtendio + ", cUsuarioCrea=" + cUsuarioCrea + ", nTiempoHolgura=" + nTiempoHolgura + ", nTramiteId=" + nTramiteId + ", nReservaCitaId=" + nReservaCitaId + ", nPrioridadId=" + nPrioridadId + '}';
    }
    
    
}
