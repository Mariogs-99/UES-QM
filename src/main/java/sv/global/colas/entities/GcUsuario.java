/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_USUARIO", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcUsuario.findAll", query = "SELECT g FROM GcUsuario g")})
public class GcUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "C_USUARIO", nullable = false, length = 256)
    private String cUsuario;
    @JoinTable(name = "GC_USR_TRA", joinColumns = {
        @JoinColumn(name = "C_USUARIO", referencedColumnName = "C_USUARIO", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "N_TRAMITE_ID", referencedColumnName = "N_TRAMITE_ID", nullable = false)})
    @ManyToMany
    private List<GcTramite> gcTramiteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cUsuario")
    private List<GcUserLog> gcUserLogList;
    @JoinColumn(name = "N_ESCRITORIO_ID", referencedColumnName = "N_ESCRITORIO_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcEscritorio nEscritorioId;

    public GcUsuario() {
    }

    public GcUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public String getCUsuario() {
        return cUsuario;
    }

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public List<GcTramite> getGcTramiteList() {
        return gcTramiteList;
    }

    public void setGcTramiteList(List<GcTramite> gcTramiteList) {
        this.gcTramiteList = gcTramiteList;
    }

    public List<GcUserLog> getGcUserLogList() {
        return gcUserLogList;
    }

    public void setGcUserLogList(List<GcUserLog> gcUserLogList) {
        this.gcUserLogList = gcUserLogList;
    }

    public GcEscritorio getNEscritorioId() {
        return nEscritorioId;
    }

    public void setNEscritorioId(GcEscritorio nEscritorioId) {
        this.nEscritorioId = nEscritorioId;
    }
    
    @PreRemove
    private void removeTramites() {
        for (GcTramite gc : gcTramiteList) {
            gc.getGcUsuarioList().remove(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cUsuario != null ? cUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcUsuario)) {
            return false;
        }
        GcUsuario other = (GcUsuario) object;
        if ((this.cUsuario == null && other.cUsuario != null) || (this.cUsuario != null && !this.cUsuario.equals(other.cUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcUsuario[ cUsuario=" + cUsuario + " ]";
    }
    
}
