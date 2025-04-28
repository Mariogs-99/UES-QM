/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_JERARQUIA_SECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GcJerarquiaSeccion.findAll", query = "SELECT g FROM GcJerarquiaSeccion g")})
public class GcJerarquiaSeccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_JRQ_SEC_ID", nullable = false)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeJerarquiaSec")
    //@SequenceGenerator(name = "SeqGeJerarquiaSec", sequenceName = "SEQ_GC_JERARQUIA_SECCION")
    private Long nJrqSecId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "S_USUARIO", nullable = false, length = 256)
    private String sUsuario;
    @JoinColumn(name = "N_SERVICIOS_ID", referencedColumnName = "N_SERVICIOS_ID")
    @ManyToOne
    private GcServicios nServiciosId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "C_UNIDAD_RECEP", nullable = false, length = 5)
    private String cUnidadRecep;
    @Size(max = 512)
    @Column(name = "D_UNIDAD_RECEP", length = 512)
    private String dUnidadRecep;
    @Size(max = 512)
    @Column(name = "D_JERARQUIAS_SECCION", length = 512)
    private String dJerarquiasSeccion;
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
    @JoinColumn(name = "N_JERARQUIA_ID", referencedColumnName = "N_JERARQUIA_ID")
    @ManyToOne
    private GcJerarquia nJerarquiaId;

    public GcJerarquiaSeccion() {
    }

    public GcJerarquiaSeccion(Long nJrqSecId) {
        this.nJrqSecId = nJrqSecId;
    }

    public GcJerarquiaSeccion(Long nJrqSecId, String sUsuario, String cUnidadRecep) {
        this.nJrqSecId = nJrqSecId;
        this.sUsuario = sUsuario;
        this.cUnidadRecep = cUnidadRecep;
    }

    public Long getNJrqSecId() {
        return nJrqSecId;
    }

    public void setNJrqSecId(Long nJrqSecId) {
        this.nJrqSecId = nJrqSecId;
    }

    public String getSUsuario() {
        return sUsuario;
    }

    public void setSUsuario(String sUsuario) {
        this.sUsuario = sUsuario;
    }

    public GcServicios getNServiciosId() {
        return nServiciosId;
    }

    public void setNServiciosId(GcServicios nServiciosId) {
        this.nServiciosId = nServiciosId;
    }

    public String getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(String cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public String getDUnidadRecep() {
        return dUnidadRecep;
    }

    public void setDUnidadRecep(String dUnidadRecep) {
        this.dUnidadRecep = dUnidadRecep;
    }

    public String getDJerarquiasSeccion() {
        return dJerarquiasSeccion;
    }

    public void setDJerarquiasSeccion(String dJerarquiasSeccion) {
        this.dJerarquiasSeccion = dJerarquiasSeccion;
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

    public GcJerarquia getNJerarquiaId() {
        return nJerarquiaId;
    }

    public void setNJerarquiaId(GcJerarquia nJerarquiaId) {
        this.nJerarquiaId = nJerarquiaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nJrqSecId != null ? nJrqSecId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcJerarquiaSeccion)) {
            return false;
        }
        GcJerarquiaSeccion other = (GcJerarquiaSeccion) object;
        if ((this.nJrqSecId == null && other.nJrqSecId != null) || (this.nJrqSecId != null && !this.nJrqSecId.equals(other.nJrqSecId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcJerarquiaSeccion[ nJrqSecId=" + nJrqSecId + " ]";
    }
    
}
