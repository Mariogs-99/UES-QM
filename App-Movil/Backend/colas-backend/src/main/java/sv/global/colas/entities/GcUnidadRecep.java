package sv.global.colas.entities;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author Alcides Nolasco
 */
@Entity
@Table(name = "TB_UNIDAD_RECEP", catalog = "", schema = "")
public class GcUnidadRecep extends PanacheEntityBase {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)

    @Column(name = "C_UNIDAD_RECEP", unique = true, nullable = false)
    private String cunidadRecep;

    @Column(name = "C_USUARIO", length = 30)

    private String cusuario;
    @Column(name = "D_UNIDAD_RECEP", length = 256)
    private String dunidadRecep;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FH_INGRESO", length = 7)
    private Date fhingreso;
    @Column(name = "B_STATUS", nullable = false)
    private short bstatus;
    @Column(name = "M_TIPO_UNIDAD", nullable = false, length = 1)
    private String mtipoUnidad;
    @Column(name = "B_DESPLEGABLE", nullable = false)
    private Boolean bdesplegable;
    @Column(name = "C_UNIDAD", length = 1)
    private String cunidad;
    @Column(name = "C_UNIDAD_DGT", length = 5)
    private String cunidadDgt;
    @Column(name = "C_DEP_MUN", length = 4)
    private String cdepMun;
    @Column(name = "S_UBIC_GEOGRAF", length = 60)
    private String subicGeograf;
    @Column(name = "C_UNIDAD_RECEP_SUP", length = 5)
    private String cunidadRecepSup;
    @Column(name = "M_SERVICIO", length = 5)
    private String mservicio;

    public GcUnidadRecep() {
    }

    public GcUnidadRecep(String cunidadRecep) {
        this.cunidadRecep = cunidadRecep;
    }

    public GcUnidadRecep(String cunidadRecep, String cusuario,
            String dunidadRecep, short bstatus, String mtipoUnidad,
            Boolean bdesplegable) {
        this.cunidadRecep = cunidadRecep;
        this.cusuario = cusuario;
        this.dunidadRecep = dunidadRecep;
        this.bstatus = bstatus;
        this.mtipoUnidad = mtipoUnidad;
        this.bdesplegable = bdesplegable;
    }

    public GcUnidadRecep(String cunidadRecep, String cusuario,
            String dunidadRecep, Date fhingreso, short bstatus,
            String mtipoUnidad, Boolean bdesplegable, String cunidad,
            String cunidadDgt, String cdepMun, String subicGeograf) {
        this.cunidadRecep = cunidadRecep;
        this.cusuario = cusuario;
        this.dunidadRecep = dunidadRecep;
        this.fhingreso = fhingreso;
        this.bstatus = bstatus;
        this.mtipoUnidad = mtipoUnidad;
        this.bdesplegable = bdesplegable;
        this.cunidad = cunidad;
        this.cunidadDgt = cunidadDgt;
        this.cdepMun = cdepMun;
        this.subicGeograf = subicGeograf;
    }

    public String getCunidadRecep() {
        return cunidadRecep;
    }

    public void setCunidadRecep(String cunidadRecep) {
        this.cunidadRecep = cunidadRecep;
    }

    public String getCusuario() {
        return cusuario;
    }

    public void setCusuario(String cusuario) {
        this.cusuario = cusuario;
    }

    public String getDunidadRecep() {
        return dunidadRecep;
    }

    public void setDunidadRecep(String dunidadRecep) {
        this.dunidadRecep = dunidadRecep;
    }

    public Date getFhingreso() {
        return fhingreso;
    }

    public void setFhingreso(Date fhingreso) {
        this.fhingreso = fhingreso;
    }

    public short getBstatus() {
        return bstatus;
    }

    public void setBstatus(short bstatus) {
        this.bstatus = bstatus;
    }

    public String getMtipoUnidad() {
        return mtipoUnidad;
    }

    public void setMtipoUnidad(String mtipoUnidad) {
        this.mtipoUnidad = mtipoUnidad;
    }

    public Boolean getBdesplegable() {
        return bdesplegable;
    }

    public void setBdesplegable(Boolean bdesplegable) {
        this.bdesplegable = bdesplegable;
    }

    public String getCunidad() {
        return cunidad;
    }

    public void setCunidad(String cunidad) {
        this.cunidad = cunidad;
    }

    public String getCunidadDgt() {
        return cunidadDgt;
    }

    public void setCunidadDgt(String cunidadDgt) {
        this.cunidadDgt = cunidadDgt;
    }

    public String getCdepMun() {
        return cdepMun;
    }

    public void setCdepMun(String cdepMun) {
        this.cdepMun = cdepMun;
    }

    public String getSubicGeograf() {
        return subicGeograf;
    }

    public void setSubicGeograf(String subicGeograf) {
        this.subicGeograf = subicGeograf;
    }

    public String getCunidadRecepSup() {
        return cunidadRecepSup;
    }

    public void setCunidadRecepSup(String cunidadRecepSup) {
        this.cunidadRecepSup = cunidadRecepSup;
    }

    public String getMservicio() {
        return mservicio;
    }

    public void setMservicio(String mservicio) {
        this.mservicio = mservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cunidadRecep != null ? cunidadRecep.hashCode() : 0);
        return hash;
    }

}
