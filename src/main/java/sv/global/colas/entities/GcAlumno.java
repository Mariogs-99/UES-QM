/*

 */
package sv.global.colas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cnolasco
 */
@Entity
@Table(name = "gc_alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GcAlumno.findAll", query = "SELECT g FROM GcAlumno g"),
    @NamedQuery(name = "GcAlumno.findByNcarnet", query = "SELECT g FROM GcAlumno g WHERE g.ncarnet = :ncarnet"),
    @NamedQuery(name = "GcAlumno.findByNombre", query = "SELECT g FROM GcAlumno g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GcAlumno.findByApellido", query = "SELECT g FROM GcAlumno g WHERE g.apellido = :apellido"),
    @NamedQuery(name = "GcAlumno.findByFacultad", query = "SELECT g FROM GcAlumno g WHERE g.facultad = :facultad"),
    @NamedQuery(name = "GcAlumno.findByCarrera", query = "SELECT g FROM GcAlumno g WHERE g.carrera = :carrera"),
    @NamedQuery(name = "GcAlumno.findByCiclo", query = "SELECT g FROM GcAlumno g WHERE g.ciclo = :ciclo")})
public class GcAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "ncarnet")
    private String ncarnet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "facultad")
    private String facultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "carrera")
    private String carrera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ciclo")
    private String ciclo;

    public GcAlumno() {
    }

    public GcAlumno(String ncarnet) {
        this.ncarnet = ncarnet;
    }

    public GcAlumno(String ncarnet, String nombre, String apellido, String facultad, String carrera, String ciclo) {
        this.ncarnet = ncarnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.facultad = facultad;
        this.carrera = carrera;
        this.ciclo = ciclo;
    }

    public String getNcarnet() {
        return ncarnet;
    }

    public void setNcarnet(String ncarnet) {
        this.ncarnet = ncarnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ncarnet != null ? ncarnet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcAlumno)) {
            return false;
        }
        GcAlumno other = (GcAlumno) object;
        if ((this.ncarnet == null && other.ncarnet != null) || (this.ncarnet != null && !this.ncarnet.equals(other.ncarnet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.global.colas.entities.GcAlumno[ ncarnet=" + ncarnet + " ]";
    }
    
}
