package sv.global.colas.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_UNIDAD_RECEP", schema = "", uniqueConstraints = {})
public class TbUnidadRecep implements java.io.Serializable {
	
	private static final long serialVersionUID = -2100391926274416983L;

	@Id
	@Column(name = "C_UNIDAD_RECEP", unique = true, nullable = false, insertable = true, updatable = true, length = 5)
	@NotNull
	private String cunidadRecep;
	@Column(name = "C_USUARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	@NotNull
	private String cusuario;
	@Column(name = "D_UNIDAD_RECEP", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	@NotNull
	private String dunidadRecep;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_INGRESO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	private Date fhingreso;
	@Column(name = "B_STATUS", unique = false, nullable = false, insertable = true, updatable = true, precision = 1, scale = 0)
	@NotNull
	private Boolean bstatus;
	@Column(name = "M_TIPO_UNIDAD", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	@NotNull
	private String mtipoUnidad;
	@Column(name = "B_DESPLEGABLE", unique = false, nullable = false, insertable = true, updatable = true, precision = 1, scale = 0)
	@NotNull
	private Boolean bdesplegable;
	@Column(name = "C_UNIDAD", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	private String cunidad;
	@Column(name = "C_UNIDAD_DGT", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	private String cunidadDgt;
	@Column(name = "C_DEP_MUN", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	private String cdepMun;
	@Column(name = "S_UBIC_GEOGRAF", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	private String subicGeograf;
	@Column(name = "C_UNIDAD_RECEP_SUP", unique = false, length = 5)
	private String cunidadRecepSup;
	@Column(name = "M_SERVICIO", unique = false, length = 5)
	private String mservicio;

	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tbUnidadRecep")
	private Set<RcTramite> rcTramites = new HashSet<RcTramite>(0);

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tbUnidadRecep")
	private Set<EdDeclaraciones> edDeclaracioneses = new HashSet<EdDeclaraciones>(
			0);
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tbUnidadRecep")
	private Set<EdNotaAbono> edNotaAbonos = new HashSet<EdNotaAbono>(0);
	
	public TbUnidadRecep() {
	}

	public TbUnidadRecep(String cunidadRecep) {
		this.cunidadRecep = cunidadRecep;
	}

	public TbUnidadRecep(String cunidadRecep, String cusuario,
			String dunidadRecep, Boolean bstatus, String mtipoUnidad,
			Boolean bdesplegable) {
		this.cunidadRecep = cunidadRecep;
		this.cusuario = cusuario;
		this.dunidadRecep = dunidadRecep;
		this.bstatus = bstatus;
		this.mtipoUnidad = mtipoUnidad;
		this.bdesplegable = bdesplegable;
	}

	
	public TbUnidadRecep(String cunidadRecep, String cusuario,
			String dunidadRecep, Date fhingreso, Boolean bstatus,
			String mtipoUnidad, Boolean bdesplegable, String cunidad,
			String cunidadDgt, String cdepMun, String subicGeograf,
			
			Set<RcTramite> rcTramites,
			Set<EdDeclaraciones> edDeclaracioneses,
			Set<EdNotaAbono> edNotaAbonos) {
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
		this.rcTramites = rcTramites;
		this.edDeclaracioneses = edDeclaracioneses;		
		this.edNotaAbonos = edNotaAbonos;
	}
	
	public String getCunidadRecep() {
		return this.cunidadRecep;
	}

	public void setCunidadRecep(String cunidadRecep) {
		this.cunidadRecep = cunidadRecep;
	}

	
	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	
	public String getDunidadRecep() {
		return this.dunidadRecep;
	}

	public void setDunidadRecep(String dunidadRecep) {
		this.dunidadRecep = dunidadRecep;
	}

	
	public Date getFhingreso() {
		return this.fhingreso;
	}

	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}

	
	public Boolean getBstatus() {
		return this.bstatus;
	}

	public void setBstatus(Boolean bstatus) {
		this.bstatus = bstatus;
	}

	
	public String getMtipoUnidad() {
		return this.mtipoUnidad;
	}

	public void setMtipoUnidad(String mtipoUnidad) {
		this.mtipoUnidad = mtipoUnidad;
	}

	
	public Boolean getBdesplegable() {
		return this.bdesplegable;
	}

	public void setBdesplegable(Boolean bdesplegable) {
		this.bdesplegable = bdesplegable;
	}

	
	public String getCunidad() {
		return this.cunidad;
	}

	public void setCunidad(String cunidad) {
		this.cunidad = cunidad;
	}

	
	public String getCunidadDgt() {
		return this.cunidadDgt;
	}

	public void setCunidadDgt(String cunidadDgt) {
		this.cunidadDgt = cunidadDgt;
	}

	
	public String getCdepMun() {
		return this.cdepMun;
	}

	public void setCdepMun(String cdepMun) {
		this.cdepMun = cdepMun;
	}

	
	public String getSubicGeograf() {
		return this.subicGeograf;
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
        
	public Set<RcTramite> getRcTramites() {
		return this.rcTramites;
	}

	public void setRcTramites(Set<RcTramite> rcTramites) {
		this.rcTramites = rcTramites;
	}

	public Set<EdDeclaraciones> getEdDeclaracioneses() {
		return this.edDeclaracioneses;
	}

	public void setEdDeclaracioneses(Set<EdDeclaraciones> edDeclaracioneses) {
		this.edDeclaracioneses = edDeclaracioneses;
	}	

	public Set<EdNotaAbono> getEdNotaAbonos() {
		return this.edNotaAbonos;
	}

	public void setEdNotaAbonos(Set<EdNotaAbono> edNotaAbonos) {
		this.edNotaAbonos = edNotaAbonos;
	}
	
}
