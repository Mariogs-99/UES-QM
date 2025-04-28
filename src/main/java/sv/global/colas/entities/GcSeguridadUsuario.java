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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlTransient;
//import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Pino
 */
@Entity
@Table(name = "GC_SEGURIDAD_USUARIO", catalog = "", schema = "")
@NamedQueries({@NamedQuery(name = "GcSeguridadUsuario.findAll", query = "SELECT g FROM GcSeguridadUsuario g")})
public class GcSeguridadUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "ID_USUARIO", nullable = false, precision = 0, scale = -127)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGcSeguridadUsuario")
    //@SequenceGenerator(name = "SeqGcSeguridadUsuario", sequenceName = "SEQ_GC_SEGURIDAD_USUARIO")
    private Long idUsuario;
    @Size(max = 100)
    @Column(name = "NOMBRE", length = 100)
    private String nombre;
    @Size(max = 100)
    @Column(name = "CORREO", length = 100)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "CONTRASENA", nullable = false, length = 256)
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Size(max = 45)
    @Column(name = "USUARIO", length = 45)
    private String usuario;
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
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)}, inverseJoinColumns = {
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false)})
    @ManyToMany
    private List<GcSeguridadRole> gcSeguridadRoleList;
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP")
    @ManyToOne
    private TbUnidadRecep cUnidadRecep;

    public GcSeguridadUsuario() {
    }

    public GcSeguridadUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public GcSeguridadUsuario(Long idUsuario, String contrasena, Date fechaIngreso) {
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public short getbActiva() {
        return bActiva;
    }

    public void setbActiva(short bActiva) {
        this.bActiva = bActiva;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

//    @XmlTransient
//    @JsonIgnore
//    public List<GcSeguridadRole> getGcSeguridadRoleList() {
//        return gcSeguridadRoleList;
//    }

    public void setGcSeguridadRoleList(List<GcSeguridadRole> gcSeguridadRoleList) {
        this.gcSeguridadRoleList = gcSeguridadRoleList;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcSeguridadUsuario)) {
            return false;
        }
        GcSeguridadUsuario other = (GcSeguridadUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GcSeguridadUsuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", correo=" + correo + ", contrasena=" + contrasena + ", bActiva=" + bActiva + ", fechaIngreso=" + fechaIngreso + ", usuario=" + usuario + ", usuarioIngreso=" + usuarioIngreso + ", fechaModifica=" + fechaModifica + ", usuarioModifica=" + usuarioModifica + ", gcSeguridadRoleList=" + gcSeguridadRoleList + ", cUnidadRecep=" + cUnidadRecep + '}';
    }
    
}
