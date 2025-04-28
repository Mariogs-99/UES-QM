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

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_ALERTA", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcAlerta.findAll", query = "SELECT g FROM GcAlerta g")})
public class GcAlerta implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id()
    @Basic(optional = false)
    @Column(name = "N_ALERTA_ID", nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGcAlerta")
    //@SequenceGenerator(name = "SeqGcAlerta", sequenceName = "SEQ_GC_ALERTA")
    private Long nAlertaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_SERVICIOS_ID", nullable = false)
    private Integer nServiciosId;
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "S_MENSAJE", nullable = false, length = 1024)
    private String sMensaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "S_TIPO", nullable = false, length = 15)
    private String sTipo;
    @Column(name = "FH_NOTIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_CORREO", nullable = false)
    private Boolean bCorreo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_PANTALLA", nullable = false)
    private Boolean bPantalla;
    @Size(max = 1024)
    @Column(name = "C_USUARIO_NOTIFICA", length = 1024)
    private String cUsuarioNotifica;
    @Size(max = 1024)
    @Column(name = "S_CORREO_NOTIFICA", length = 1024)
    private String sCorreoNotifica;
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

    public GcAlerta() {
    }

    public GcAlerta(Long nAlertaId) {
        this.nAlertaId = nAlertaId;
    }

    public GcAlerta(Long nAlertaId, Integer nServiciosId, String sMensaje, String sTipo, Boolean bCorreo, Boolean bPantalla, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, short bActiva) {
        this.nAlertaId = nAlertaId;
        this.nServiciosId = nServiciosId;
        this.sMensaje = sMensaje;
        this.sTipo = sTipo;
        this.bCorreo = bCorreo;
        this.bPantalla = bPantalla;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.bActiva = bActiva;
    }

    public Long getNAlertaId() {
        return nAlertaId;
    }

    public void setNAlertaId(Long nAlertaId) {
        this.nAlertaId = nAlertaId;
    }

    public Integer getNServiciosId() {
        return nServiciosId;
    }

    public void setNServiciosId(Integer nServiciosId) {
        this.nServiciosId = nServiciosId;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public String getSMensaje() {
        return sMensaje;
    }

    public void setSMensaje(String sMensaje) {
        this.sMensaje = sMensaje;
    }

    public String getSTipo() {
        return sTipo;
    }

    public void setSTipo(String sTipo) {
        this.sTipo = sTipo;
    }

    public Date getFhNotificacion() {
        return fhNotificacion;
    }

    public void setFhNotificacion(Date fhNotificacion) {
        this.fhNotificacion = fhNotificacion;
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

    public String getCUsuarioNotifica() {
        return cUsuarioNotifica;
    }

    public void setCUsuarioNotifica(String cUsuarioNotifica) {
        this.cUsuarioNotifica = cUsuarioNotifica;
    }

    public String getSCorreoNotifica() {
        return sCorreoNotifica;
    }

    public void setSCorreoNotifica(String sCorreoNotifica) {
        this.sCorreoNotifica = sCorreoNotifica;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nAlertaId != null ? nAlertaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcAlerta)) {
            return false;
        }
        GcAlerta other = (GcAlerta) object;
        if ((this.nAlertaId == null && other.nAlertaId != null) || (this.nAlertaId != null && !this.nAlertaId.equals(other.nAlertaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcAlerta[ nAlertaId=" + nAlertaId + " ]";
    }
    
}
