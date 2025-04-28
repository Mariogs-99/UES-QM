package sv.global.colas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Florentin
 */
@Entity
@Table(name = "GC_EVENTOS", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GcEventos.findAll", query = "SELECT g FROM GcEventos g"),
    @NamedQuery(name = "GcEventos.findByNEventoId", query = "SELECT g FROM GcEventos g WHERE g.nEventoId = :nEventoId"),
    @NamedQuery(name = "GcEventos.findByDEventos", query = "SELECT g FROM GcEventos g WHERE g.dEventos = :dEventos")})
public class GcEventos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_EVENTO_ID", nullable = false, precision = 0, scale = -127)
    private Long nEventoId;
	
    @Column(name = "D_EVENTOS", length = 256)
    private String dEventos;
    @OneToMany(mappedBy = "nEventoId")
    private List<GcUserLog> gcUserLogList;

    public String getdEventos() {
        return dEventos;
    }

    public void setdEventos(String dEventos) {
        this.dEventos = dEventos;
    }

    public GcEventos() {
    }

    public GcEventos(Long nEventoId) {
        this.nEventoId = nEventoId;
    }

    public Long getNEventoId() {
        return nEventoId;
    }

    public void setNEventoId(Long nEventoId) {
        this.nEventoId = nEventoId;
    }

    public String getDEventos() {
        return dEventos;
    }

    public void setDEventos(String dEventos) {
        this.dEventos = dEventos;
    }

    @XmlTransient
    public List<GcUserLog> getGcUserLogList() {
        return gcUserLogList;
    }

    public void setGcUserLogList(List<GcUserLog> gcUserLogList) {
        this.gcUserLogList = gcUserLogList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nEventoId != null ? nEventoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcEventos)) {
            return false;
        }
        GcEventos other = (GcEventos) object;
        if ((this.nEventoId == null && other.nEventoId != null) || (this.nEventoId != null && !this.nEventoId.equals(other.nEventoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.GcEventos[ nEventoId=" + nEventoId + " ]";
    }
    
}
