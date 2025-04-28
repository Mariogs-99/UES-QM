/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ever.argueta
 */
@Embeddable
public class GcNotificacionId implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ALERTA_ID", nullable = false)
    private Long nAlertaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "C_USUARIO", nullable = false, length = 256)
    private String cUsuario;

    public GcNotificacionId() {
    }

    public GcNotificacionId(Long nAlertaId, String cUsuario) {
        this.nAlertaId = nAlertaId;
        this.cUsuario = cUsuario;
    }

    public Long getNAlertaId() {
        return nAlertaId;
    }

    public void setNAlertaId(Long nAlertaId) {
        this.nAlertaId = nAlertaId;
    }

    public String getCUsuario() {
        return cUsuario;
    }

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nAlertaId != null ? nAlertaId.hashCode() : 0);
        hash += (cUsuario != null ? cUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcNotificacionId)) {
            return false;
        }
        GcNotificacionId other = (GcNotificacionId) object;
        if ((this.nAlertaId == null && other.nAlertaId != null) || (this.nAlertaId != null && !this.nAlertaId.equals(other.nAlertaId))) {
            return false;
        }
        if ((this.cUsuario == null && other.cUsuario != null) || (this.cUsuario != null && !this.cUsuario.equals(other.cUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcNotificacionId[ nAlertaId=" + nAlertaId + ", cUsuario=" + cUsuario + " ]";
    }
    
}
