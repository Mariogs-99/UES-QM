/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_TIPO_EQUIPO", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcTipoEquipo.findAll", query = "SELECT g FROM GcTipoEquipo g")})
public class GcTipoEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_TIPO_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeTipoEquipo")
    //@SequenceGenerator(name = "SeqGeTipoEquipo", sequenceName = "SEQ_GC_TIPO_EQUIPO")
    private Long nTipoId;
    @Size(max = 512)
    @Column(name = "S_NOMBRE", length = 256)
    private String sNombre;
    @Size(max = 512)
    @Column(name = "D_TIPO_EQUIPO", length = 512)
    private String dTipoEquipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nTipoId")
    private List<GcEquipos> gcEquiposList;

    public GcTipoEquipo() {
    }

    public GcTipoEquipo(Long nTipoId) {
        this.nTipoId = nTipoId;
    }

    public Long getNTipoId() {
        return nTipoId;
    }

    public void setNTipoId(Long nTipoId) {
        this.nTipoId = nTipoId;
    }

    public String getSNombre() {
        return sNombre;
    }

    public void setSNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getDTipoEquipo() {
        return dTipoEquipo;
    }

    public void setDTipoEquipo(String dTipoEquipo) {
        this.dTipoEquipo = dTipoEquipo;
    }

    public List<GcEquipos> getGcEquiposList() {
        return gcEquiposList;
    }

    public void setGcEquiposList(List<GcEquipos> gcEquiposList) {
        this.gcEquiposList = gcEquiposList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nTipoId != null ? nTipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcTipoEquipo)) {
            return false;
        }
        GcTipoEquipo other = (GcTipoEquipo) object;
        if ((this.nTipoId == null && other.nTipoId != null) || (this.nTipoId != null && !this.nTipoId.equals(other.nTipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcTipoEquipo[ nTipoId=" + nTipoId + " ]";
    }
    
}
