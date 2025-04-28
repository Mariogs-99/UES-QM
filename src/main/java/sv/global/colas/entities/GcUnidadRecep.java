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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "TB_UNIDAD_RECEP", catalog = "", schema = "")
@NamedQueries({
@NamedQuery(name = "GcUnidadRecep.findAll", query = "SELECT g FROM GcZona g")})
public class GcUnidadRecep  implements Serializable {
    private static final long serialVersionUID = 1L;
    
        @Id
        @Basic(optional = false)
        @NotNull
        @Column(name = "C_UNIDAD_RECEP",unique=true, nullable = false)
        private String cunidadRecep;
        @Size(min = 1, max = 30)
        @Column(name = "C_USUARIO", length = 30)
	@NotNull
	private String cusuario;
        @Size(min = 1, max = 256)
	@Column(name = "D_UNIDAD_RECEP", length = 256)
	@NotNull
	private String dunidadRecep;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_INGRESO", length = 7)
	private Date fhingreso;
        @Basic(optional = false)
        @NotNull
        @Column(name = "B_STATUS", nullable = false)
	private short bstatus;
	@Column(name = "M_TIPO_UNIDAD", nullable = false, length = 1)
	@NotNull
	private String mtipoUnidad;
	@Column(name = "B_DESPLEGABLE",nullable = false)
	@NotNull
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
        
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "cUnidadRecep")
        private List<GcZona> gcZonaList;

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

    public List<GcZona> getGcZonaList() {
        return gcZonaList;
    }

    public void setGcZonaList(List<GcZona> gcZonaList) {
        this.gcZonaList = gcZonaList;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cunidadRecep != null ? cunidadRecep.hashCode() : 0);
        return hash;
    }
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcUnidadRecep)) {
            return false;
        }
        GcUnidadRecep other = (GcUnidadRecep) object;
        if ((this.cunidadRecep == null && other.cunidadRecep != null) || (this.cunidadRecep != null && !this.cunidadRecep.equals(other.cunidadRecep))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcUnidadRecep[ cunidadRecep=" + cunidadRecep + " ]";
    }    
    
}
