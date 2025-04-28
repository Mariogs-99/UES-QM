/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ever Argueta
 */
@Entity
@Table(name = "GC_SEGURIDAD_ROLE", catalog = "", schema = "")
@NamedQueries({ @NamedQuery(name = "GcSeguridadRole.findAll", query = "SELECT g FROM GcSeguridadRole g")})
public class GcSeguridadRole implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLE", nullable = false, precision = 0, scale = -127)
    private Long idRole;
    @Size(max = 45)
    @Column(name = "NOMBRE", length = 45)
    private String nombre;
    @Size(max = 256)
    @Column(name = "DESCRIPCION", length = 256)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 100)
    @Column(name = "USUARIO_INGRESO", length = 100)
    private String usuarioIngreso;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    @Size(max = 100)
    @Column(name = "USUARIO_MODIFICA", length = 100)
    private String usuarioModifica;
    @JoinTable(name = "GC_SEGURIDAD_USUARIO_ROLE", joinColumns = {
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false)}, inverseJoinColumns = {
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)})
    @ManyToMany
    private List<GcSeguridadUsuario> gcSeguridadUsuarioList;

    public GcSeguridadRole() {
    }

    public GcSeguridadRole(Long idRole) {
        this.idRole = idRole;
    }

    public GcSeguridadRole(Long idRole, Date fechaIngreso) {
        this.idRole = idRole;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(String usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<GcSeguridadUsuario> getGcSeguridadUsuarioList() {
//        return gcSeguridadUsuarioList;
//    }

    public void setGcSeguridadUsuarioList(List<GcSeguridadUsuario> gcSeguridadUsuarioList) {
        this.gcSeguridadUsuarioList = gcSeguridadUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcSeguridadRole)) {
            return false;
        }
        GcSeguridadRole other = (GcSeguridadRole) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.global.colas.entities.GcSeguridadRole[ idRole=" + idRole + " ]";
    }
    
}
