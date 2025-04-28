/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_ESTADO", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcEstado.findAll", query = "SELECT g FROM GcEstado g")})
public class GcEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ESTADO_ID", nullable = false, precision = 22, scale = 0)
    private Long nEstadoId;
    @Size(max = 256)
    @Column(name = "D_ESTADO", length = 256)
    private String dEstado;

    public GcEstado() {
    }

    public GcEstado(Long nEstadoId) {
        this.nEstadoId = nEstadoId;
    }

    public Long getNEstadoId() {
        return nEstadoId;
    }

    public void setNEstadoId(Long nEstadoId) {
        this.nEstadoId = nEstadoId;
    }

    public String getDEstado() {
        return dEstado;
    }

    public void setDEstado(String dEstado) {
        this.dEstado = dEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nEstadoId != null ? nEstadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcEstado)) {
            return false;
        }
        GcEstado other = (GcEstado) object;
        if ((this.nEstadoId == null && other.nEstadoId != null) || (this.nEstadoId != null && !this.nEstadoId.equals(other.nEstadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcEstado[ nEstadoId=" + nEstadoId + " ]";
    }
    
}
