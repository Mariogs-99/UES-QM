/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_JERARQUIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GcJerarquia.findAll", query = "SELECT g FROM GcJerarquia g")})
public class GcJerarquia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "N_JERARQUIA_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeJerarquia")
    //@SequenceGenerator(name = "SeqGeJerarquia", sequenceName = "SEQ_GC_JERARQUIA")
    private Long nJerarquiaId;
    @Size(max = 512)
    @Column(name = "S_DESCRIPCION", length = 512)
    private String sDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "S_JERARQUIA", nullable = false, length = 100)
    private String sJerarquia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @Column(name = "FI_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiVigencia;
    @Column(name = "FF_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffVigencia;
    @Size(max = 100)
    @Column(name = "C_USUARIO_MODIFICA", length = 100)
    private String cUsuarioModifica;
    @Size(max = 100)
    @Column(name = "C_USUARIO_CREA", length = 100)
    private String cUsuarioCrea;
    @OneToMany(mappedBy = "nJerarquiaId")
    private List<GcJerarquiaSeccion> gcJerarquiaSeccionList;

    public GcJerarquia() {
    }

    public GcJerarquia(Long nJerarquiaId) {
        this.nJerarquiaId = nJerarquiaId;
    }

    public GcJerarquia(Long nJerarquiaId, String sJerarquia, short bActiva) {
        this.nJerarquiaId = nJerarquiaId;
        this.sJerarquia = sJerarquia;
        this.bActiva = bActiva;
    }

    public Long getNJerarquiaId() {
        return nJerarquiaId;
    }

    public void setNJerarquiaId(Long nJerarquiaId) {
        this.nJerarquiaId = nJerarquiaId;
    }

    public String getSDescripcion() {
        return sDescripcion;
    }

    public void setSDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public String getSJerarquia() {
        return sJerarquia;
    }

    public void setSJerarquia(String sJerarquia) {
        this.sJerarquia = sJerarquia;
    }

    public short getBActiva() {
        return bActiva;
    }

    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
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

    public String getCUsuarioModifica() {
        return cUsuarioModifica;
    }

    public void setCUsuarioModifica(String cUsuarioModifica) {
        this.cUsuarioModifica = cUsuarioModifica;
    }

    public String getCUsuarioCrea() {
        return cUsuarioCrea;
    }

    public void setCUsuarioCrea(String cUsuarioCrea) {
        this.cUsuarioCrea = cUsuarioCrea;
    }

    @XmlTransient
    @JsonIgnore
    public List<GcJerarquiaSeccion> getGcJerarquiaSeccionList() {
        return gcJerarquiaSeccionList;
    }

    public void setGcJerarquiaSeccionList(List<GcJerarquiaSeccion> gcJerarquiaSeccionList) {
        this.gcJerarquiaSeccionList = gcJerarquiaSeccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nJerarquiaId != null ? nJerarquiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcJerarquia)) {
            return false;
        }
        GcJerarquia other = (GcJerarquia) object;
        if ((this.nJerarquiaId == null && other.nJerarquiaId != null) || (this.nJerarquiaId != null && !this.nJerarquiaId.equals(other.nJerarquiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcJerarquia[ nJerarquiaId=" + nJerarquiaId + " ]";
    }
    
}
