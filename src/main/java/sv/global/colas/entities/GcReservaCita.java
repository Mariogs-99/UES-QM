/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
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
@Table(name = "GC_RESERVA_CITA", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcReservaCita.findAll", query = "SELECT g FROM GcReservaCita g")})
public class GcReservaCita implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "N_RESERVA_CITA_ID", nullable = false, precision = 22)
    private Long nReservaCitaId;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FH_RESERVACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhReservacion;
    @Size(max = 256)
    @Column(name = "S_CORREO", length = 256)
    private String sCorreo;
    @Size(max = 14)
    @Column(name = "NIT", length = 14)
    private String nit;
    @Size(max = 25)
    @Column(name = "N_TELEFONO", length = 25)
    private String nTelefono;
    @Size(max = 1024)
    @Column(name = "S_OBSERVACIONES", length = 1024)
    private String sObservaciones;
    @Size(max = 50)
    @Column(name = "S_COD_VERIFICA", length = 50)
    private String sCodVerifica;
    @Column(name = "B_ESTADO")
    private Integer bEstado;
    
    @JoinColumn(name = "N_TRAMITE_ID", referencedColumnName = "N_TRAMITE_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcTramite nTramiteId;
    
    @OneToMany(mappedBy = "nReservaCitaId")
    private List<GcTiquete> gcTiqueteList;

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

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
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

    public GcTramite getNTramiteId() {
        return nTramiteId;
    }

    public void setNTramiteId(GcTramite nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public List<GcTiquete> getGcTiqueteList() {
        return gcTiqueteList;
    }

    public void setGcTiqueteList(List<GcTiquete> gcTiqueteList) {
        this.gcTiqueteList = gcTiqueteList;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcReservaCita)) {
            return false;
        }
        GcReservaCita other = (GcReservaCita) object;
        if ((this.nReservaCitaId == null && other.nReservaCitaId != null) || (this.nReservaCitaId != null && !this.nReservaCitaId.equals(other.nReservaCitaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcReservaCita[ nReservaCitaId=" + nReservaCitaId + " ]";
    }
    
}
