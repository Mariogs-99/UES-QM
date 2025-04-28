package sv.global.colas.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ED_PLANILLA", schema = "EDADEPUDOC")
public class EdPlanilla implements java.io.Serializable {

	private static final long serialVersionUID = 8260046215539558464L;
	
	@Id
	@Column(name = "N_PLANILLA", unique = true, nullable = false, length = 12)
	@NotNull
	private String nplanilla;
	
	@Column(name = "C_USUARIO", nullable = false, length = 30)
	@NotNull
	private String cusuario;
	
	@Column(name = "C_UNIDAD_RECEP", length = 5)
	private String cunidadRecep;
	
	@Column(name = "B_STATUS_PLANILLA", nullable = false, precision = 1, scale = 0)
	@NotNull
	private Integer bstatusPlanilla;
	
	@Column(name = "B_CUADRE_PLANI", nullable = false, precision = 1, scale = 0)
	@NotNull
	private Integer bcuadrePlani;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "F_RECEP_CUMPLIM", length = 7)
	private Date frecepCumplim;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "F_DGT")
	private Date fdgt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "F_DGII")
	private Date fdgii;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "F_INGRESO")
	private Date fingreso;
	
	@Column(name = "N_NOTAS_GENERA", nullable = false, precision = 4, scale = 0)
	@NotNull
	private Integer nnotasGenera;
	
	@Column(name = "S_OBSERVACION", length = 255)
	private String sobservacion;
	
	@Column(name = "I_TECNICO", length = 10)
	private Long itecnico;
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "edPlanilla")
	private Set<EdNotaAbono> edNotaAbonos = new HashSet<EdNotaAbono>(0);

	
	// Propiedades que pertenecian al dto
	
	@Transient
	private boolean checked = false;
	
	@Transient
	private Integer bpreviousState;
	
	@Transient
	private List<TbListasValorDet> previousState;
	
	@Transient
	private String prefix;
	
	public static final Integer EST_PLANILLA_CERRADA = 0;
	public static final Integer EST_PLANILLA_PENDIENTE = 1;
	public static final Integer EST_PLANILLA_ANULADA = 2;
	public static final String EST_PLANILLA_CERRADA_DESC = "Cerrada";
	public static final String EST_PLANILLA_ANULADA_DESC = "Anulada";
	public static final String EST_PLANILLA_PENDIENTE_DESC = "Pendiente de Cerrar";
	public static final Integer EST_CUADRE_SI = 0;
	public static final String EST_CUADRE_SI_DESC = "SI";
	public static final String EST_CUADRE_NO_DESC = "NO";
	
	public EdPlanilla() {
	}

	public EdPlanilla(String nplanilla, String cusuario,
			Integer bstatusPlanilla, Integer bcuadrePlani) {
		this.nplanilla = nplanilla;
		this.cusuario = cusuario;
		this.bstatusPlanilla = bstatusPlanilla;
		this.bcuadrePlani = bcuadrePlani;
	}

	public EdPlanilla(String nplanilla, String cusuario, String cunidadRecep,
			Integer bstatusPlanilla, Integer bcuadrePlani, Date frecepCumplim,
			Date fdgt, Date fdgii,Date fingreso, Set<EdNotaAbono> edNotaAbonos) {
		this.nplanilla = nplanilla;
		this.cusuario = cusuario;
		this.cunidadRecep = cunidadRecep;
		this.bstatusPlanilla = bstatusPlanilla;
		this.bcuadrePlani = bcuadrePlani;
		this.frecepCumplim = frecepCumplim;
		this.fdgt = fdgt;
		this.fdgii = fdgii;
		this.fingreso = fingreso; 
		this.edNotaAbonos = edNotaAbonos;
	}

	
	public String getNplanilla() {
		return this.nplanilla;
	}

	public void setNplanilla(String nplanilla) {
		this.nplanilla = nplanilla;
	}

	
	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	
	public String getCunidadRecep() {
		return this.cunidadRecep;
	}

	public void setCunidadRecep(String cunidadRecep) {
		this.cunidadRecep = cunidadRecep;
	}

	
	public Integer getBstatusPlanilla() {
		return this.bstatusPlanilla;
	}

	public void setBstatusPlanilla(Integer bstatusPlanilla) {
		this.bstatusPlanilla = bstatusPlanilla;
	}

	
	public Integer getBcuadrePlani() {
		return this.bcuadrePlani;
	}

	public void setBcuadrePlani(Integer bcuadrePlani) {
		this.bcuadrePlani = bcuadrePlani;
	}

	
	public Date getFrecepCumplim() {
		return this.frecepCumplim;
	}

	public void setFrecepCumplim(Date frecepCumplim) {
		this.frecepCumplim = frecepCumplim;
	}

	
	public Date getFdgt() {
		return this.fdgt;
	}

	public void setFdgt(Date fdgt) {
		this.fdgt = fdgt;
	}

	
	public Date getFdgii() {
		return this.fdgii;
	}

	public void setFdgii(Date fdgii) {
		this.fdgii = fdgii;
	}

	
	public Date getFingreso() {
		return this.fingreso;
	}

	public void setFingreso(Date fingreso) {
		this.fingreso = fingreso;
	}
	
	public Integer getNnotasGenera() {
		return nnotasGenera;
	}

	public void setNnotasGenera(Integer nnotasGenera) {
		this.nnotasGenera = nnotasGenera;
	}

	public String getSobservacion() {
		return sobservacion;
	}
	
	public Long getItecnico() {
		return itecnico;
	}

	public void setItecnico(Long itecnico) {
		this.itecnico = itecnico;
	}

	public void setSobservacion(String sobservacion) {
		this.sobservacion = sobservacion;
	}

	public Set<EdNotaAbono> getEdNotaAbonos() {
		return this.edNotaAbonos;
	}

	public void setEdNotaAbonos(Set<EdNotaAbono> edNotaAbonos) {
		this.edNotaAbonos = edNotaAbonos;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getBpreviousState() {
		return bpreviousState;
	}

	public void setBpreviousState(Integer bpreviousState) {
		this.bpreviousState = bpreviousState;
	}

	public List<TbListasValorDet> getPreviousState() {
		return previousState;
	}

	public void setPreviousState(List<TbListasValorDet> previousState) {
		this.previousState = previousState;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}




