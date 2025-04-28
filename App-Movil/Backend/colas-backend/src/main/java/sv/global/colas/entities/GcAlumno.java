package sv.global.colas.entities;


import java.io.Serializable;
import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;


/**
 *
 * @author cnolasco
 */
@Entity
@Table(name = "gc_alumno")
public class GcAlumno extends PanacheEntityBase {

    private static final long serialVersionUID = 1L;
    @Id
  
    @Size(min = 1, max = 12)
    @Column(name = "ncarnet")
    private String ncarnet;
    
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
   
    @Size(min = 1, max = 45)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
  
    @Size(min = 1, max = 45)
    @Column(name = "facultad")
    private String facultad;
    @Basic(optional = false)
  
    @Size(min = 1, max = 45)
    @Column(name = "carrera")
    private String carrera;
    @Basic(optional = false)
   
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


 
    
}
