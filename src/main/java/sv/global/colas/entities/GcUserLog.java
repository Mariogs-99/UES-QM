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
@Table(name = "GC_USER_LOG", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcUserLog.findAll", query = "SELECT g FROM GcUserLog g")})
public class GcUserLog implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "N_EVENTO_ID", referencedColumnName = "N_EVENTO_ID", nullable = false)
    @ManyToOne(optional = false)
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "N_EVENTO_ID", nullable = false)
    private GcEventos nEventoId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_USR_LOG_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGcUserLog")
    //@SequenceGenerator(name = "SeqGcUserLog", sequenceName = "SEQ_GC_USER_LOG")
    private Long nUsrLogId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FHI_EVENTO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhiEvento;
    @Column(name = "FHF_EVENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhfEvento;
    @Size(max = 512)
    @Column(name = "S_DETALLES", length = 512)
    private String sDetalles;
    @JoinColumn(name = "C_USUARIO", referencedColumnName = "C_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private GcUsuario cUsuario;
  

    public GcUserLog() {
    }

    public GcUserLog(Long nUsrLogId) {
        this.nUsrLogId = nUsrLogId;
    }

    public GcUserLog(Long nUsrLogId, GcEventos nEventoId, Date fEvento) {
        this.nUsrLogId = nUsrLogId;
        this.nEventoId = nEventoId;
        this.fhiEvento = fEvento;
    }

    public GcEventos getNEventoId() {
        return nEventoId;
    }

    public void setNEventoId(GcEventos nEventoId) {
        this.nEventoId = nEventoId;
    }

    public Long getNUsrLogId() {
        return nUsrLogId;
    }

    public void setNUsrLogId(Long nUsrLogId) {
        this.nUsrLogId = nUsrLogId;
    }

    

    public String getSDetalles() {
        return sDetalles;
    }

    public void setSDetalles(String sDetalles) {
        this.sDetalles = sDetalles;
    }

    public GcUsuario getCUsuario() {
        return cUsuario;
    }

    public void setCUsuario(GcUsuario cUsuario) {
        this.cUsuario = cUsuario;
    }

    public GcEventos getnEventoId() {
		return nEventoId;
	}

	public void setnEventoId(GcEventos nEventoId) {
		this.nEventoId = nEventoId;
	}

	public Date getFhiEvento() {
		return fhiEvento;
	}

	public void setFhiEvento(Date fhiEvento) {
		this.fhiEvento = fhiEvento;
	}

	public Date getFhfEvento() {
		return fhfEvento;
	}

	public void setFhfEvento(Date fhfEvento) {
		this.fhfEvento = fhfEvento;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (nUsrLogId != null ? nUsrLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcUserLog)) {
            return false;
        }
        GcUserLog other = (GcUserLog) object;
        if ((this.nUsrLogId == null && other.nUsrLogId != null) || (this.nUsrLogId != null && !this.nUsrLogId.equals(other.nUsrLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcUserLog[ nUsrLogId=" + nUsrLogId + " ]";
    }
    
}
