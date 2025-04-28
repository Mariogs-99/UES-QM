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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "GC_PRIORIDAD", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcPrioridad.findAll", query = "SELECT g FROM GcPrioridad g")})
public class GcPrioridad implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_PRIORIDAD_ID", nullable = false, precision = 22)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGePrioridad")
    //@SequenceGenerator(name = "SeqGePrioridad", sequenceName = "SEQ_GC_PRIORIDAD")
    private Long nPrioridadId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_PESO", nullable = false)
    private Integer nPeso;
    @Size(max = 256)
    @Column(name = "S_NOMBRE", length = 256)
    private String sNombre;
    @Size(max = 256)
    @Column(name = "D_PRIORIDAD", length = 256)
    private String dPrioridad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C_USUARIO_CREA", nullable = false, length = 100)
    private String cUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C_USUARIO_MODI", nullable = false, length = 100)
    private String cUsuarioModi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_VIGENCIA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiVigencia;
    @Column(name = "FF_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffVigencia;
    @Column(name = "F_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fModifica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_FILA_ESP", nullable = false)
    private Long bFilaEsp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nPrioridadId")
    private List<GcTiquete> gcTiqueteList;

    public GcPrioridad() {
    }

    public GcPrioridad(Long nPrioridadId) {
        this.nPrioridadId = nPrioridadId;
    }

    public GcPrioridad(Long nPrioridadId, Integer nPeso, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, short bActiva, String sNombre) {
        this.nPrioridadId = nPrioridadId;
        this.nPeso = nPeso;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.bActiva = bActiva;
        this.sNombre=sNombre;
    }

    public Long getNPrioridadId() {
        return nPrioridadId;
    }

    public void setNPrioridadId(Long nPrioridadId) {
        this.nPrioridadId = nPrioridadId;
    }

    public Integer getNPeso() {
        return nPeso;
    }

    public void setNPeso(Integer nPeso) {
        this.nPeso = nPeso;
    }

    public String getSNombre() {
        return sNombre;
    }

    public void setSNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getDPrioridad() {
        return dPrioridad;
    }

    public void setDPrioridad(String dPrioridad) {
        this.dPrioridad = dPrioridad;
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

    public List<GcTiquete> getGcTiqueteList() {
        return gcTiqueteList;
    }

    public void setGcTiqueteList(List<GcTiquete> gcTiqueteList) {
        this.gcTiqueteList = gcTiqueteList;
    }

    public Long getBFilaEsp() {
        return bFilaEsp;
    }

    public void setBFilaEsp(Long bFilaEsp) {
        this.bFilaEsp = bFilaEsp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nPrioridadId != null ? nPrioridadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcPrioridad)) {
            return false;
        }
        GcPrioridad other = (GcPrioridad) object;
        if ((this.nPrioridadId == null && other.nPrioridadId != null) || (this.nPrioridadId != null && !this.nPrioridadId.equals(other.nPrioridadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcPrioridad[ nPrioridadId=" + nPrioridadId + " ]";
    }
    
}
