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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author ever.argueta
 */
@Entity
@Table(name = "GC_NOTIFICACION", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcNotificacion.findAll", query = "SELECT g FROM GcNotificacion g")})
public class GcNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GcNotificacionId gcNotificacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_CORREO", nullable = false)
    private Boolean bCorreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_PANTALLA", nullable = false)
    private Boolean bPantalla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FH_NOTIFICACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhNotificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "M_ESTADO", nullable = false, length = 1)
    private String mEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "C_USUARIO", nullable = false, length = 256, insertable = false, updatable = false)
    private String gcUsuario;
    @JoinColumn(name = "N_ALERTA_ID", referencedColumnName = "N_ALERTA_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GcAlerta gcAlerta;

    public GcNotificacion() {
    }

    public GcNotificacion(GcNotificacionId gcNotificacionId) {
        this.gcNotificacionId = gcNotificacionId;
    }

    public GcNotificacion(GcNotificacionId gcNotificacionId, Boolean bCorreo, Boolean bPantalla, Date fhNotificacion, String mEstado) {
        this.gcNotificacionId = gcNotificacionId;
        this.bCorreo = bCorreo;
        this.bPantalla = bPantalla;
        this.fhNotificacion = fhNotificacion;
        this.mEstado = mEstado;
    }

    public GcNotificacion(Long nAlertaId, String cUsuario) {
        this.gcNotificacionId = new GcNotificacionId(nAlertaId, cUsuario);
    }

    public GcNotificacionId getGcNotificacionId() {
        return gcNotificacionId;
    }

    public void setGcNotificacionId(GcNotificacionId gcNotificacionId) {
        this.gcNotificacionId = gcNotificacionId;
    }

    public Boolean getBCorreo() {
        return bCorreo;
    }

    public void setBCorreo(Boolean bCorreo) {
        this.bCorreo = bCorreo;
    }

    public Boolean getBPantalla() {
        return bPantalla;
    }

    public void setBPantalla(Boolean bPantalla) {
        this.bPantalla = bPantalla;
    }

    public Date getFhNotificacion() {
        return fhNotificacion;
    }

    public void setFhNotificacion(Date fhNotificacion) {
        this.fhNotificacion = fhNotificacion;
    }

    public String getMEstado() {
        return mEstado;
    }

    public void setMEstado(String mEstado) {
        this.mEstado = mEstado;
    }

    public String getGcUsuario() {
        return gcUsuario;
    }

    public void setGcUsuario(String gcUsuario) {
        this.gcUsuario = gcUsuario;
    }

    public GcAlerta getGcAlerta() {
        return gcAlerta;
    }

    public void setGcAlerta(GcAlerta gcAlerta) {
        this.gcAlerta = gcAlerta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcNotificacionId != null ? gcNotificacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcNotificacion)) {
            return false;
        }
        GcNotificacion other = (GcNotificacion) object;
        if ((this.gcNotificacionId == null && other.gcNotificacionId != null) || (this.gcNotificacionId != null && !this.gcNotificacionId.equals(other.gcNotificacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcNotificacion[ gcNotificacionId=" + gcNotificacionId + " ]";
    }
    
}
