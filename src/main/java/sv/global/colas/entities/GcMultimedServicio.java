/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "GC_MULTIMED_SERVICIO", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcMultimedServicio.findAll", query = "SELECT g FROM GcMultimedServicio g")})
public class GcMultimedServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    private TbUnidadRecep cUnidadRecep;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_MULTIMEDIA_ID", nullable = false, precision = 22, scale = 0)
    private Long nMultimediaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ORDEN", nullable = false)   
    private Long nOrden;
//    @ManyToMany(mappedBy = "gcConfReproList")
//    private List<GcMultimedia> gcMultimediaList;

    public GcMultimedServicio() {
    }

    public GcMultimedServicio(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public GcMultimedServicio(Long nOrden) {
        this.nOrden = nOrden;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public Long getNOrden() {
        return nOrden;
    }

    public void setNOrden(Long nOrden) {
        this.nOrden = nOrden;
    }

//    public List<GcMultimedia> getGcMultimediaList() {
//        return gcMultimediaList;
//    }
//
//    public void setGcMultimediaList(List<GcMultimedia> gcMultimediaList) {
//        this.gcMultimediaList = gcMultimediaList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cUnidadRecep != null ? cUnidadRecep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcMultimedServicio)) {
            return false;
        }
        GcMultimedServicio other = (GcMultimedServicio) object;
        if ((this.cUnidadRecep == null && other.cUnidadRecep != null) || (this.cUnidadRecep != null && !this.cUnidadRecep.equals(other.cUnidadRecep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcConfRepro[ cUnidadRecep=" + cUnidadRecep + " ]";
    }
    
}
