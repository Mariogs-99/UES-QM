package sv.global.colas.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_TRAMITE_WEB_DET", schema = "CATALOGOS")
public class TbTramiteWebDet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ITramiteWebDet;
	private TbTramiteWeb tbTramiteWeb;
	private String SLogTrazabilidad;
	private Date fhIngreso;
	private String CUsuario;
	private Integer BActivo;
	private Integer BTipoUsuario;
	private String STitulo;
	private Integer BEstado;
	private String SMensajeTrazabilidad;
	private Set<DeTrazabilidad> deTrazabilidads = new HashSet<DeTrazabilidad>(0);

	public TbTramiteWebDet() {

	}

	@Id
	@Column(name = "I_TRAMITE_WEB_DET", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getITramiteWebDet() {
		return this.ITramiteWebDet;
	}

	public void setITramiteWebDet(Long ITramiteWebDet) {
		this.ITramiteWebDet = ITramiteWebDet;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "I_TRAMITE_WEB", nullable = false)
	public TbTramiteWeb getTbTramiteWeb() {
		return this.tbTramiteWeb;
	}

	public void setTbTramiteWeb(TbTramiteWeb tbTramiteWeb) {
		this.tbTramiteWeb = tbTramiteWeb;
	}

	@Column(name = "S_LOG_TRAZABILIDAD", nullable = false, length = 150)
	public String getSLogTrazabilidad() {
		return this.SLogTrazabilidad;
	}

	public void setSLogTrazabilidad(String SLogTrazabilidad) {
		this.SLogTrazabilidad = SLogTrazabilidad;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FH_INGRESO", nullable = false, length = 7)
	public Date getFhIngreso() {
		return this.fhIngreso;
	}

	public void setFhIngreso(Date fhIngreso) {
		this.fhIngreso = fhIngreso;
	}

	@Column(name = "C_USUARIO", nullable = false, length = 60)
	public String getCUsuario() {
		return this.CUsuario;
	}

	public void setCUsuario(String CUsuario) {
		this.CUsuario = CUsuario;
	}

	@Column(name = "B_ACTIVO", nullable = false, precision = 1, scale = 0)
	public Integer getBActivo() {
		return this.BActivo;
	}

	public void setBActivo(Integer BActivo) {
		this.BActivo = BActivo;
	}

	@Column(name = "B_TIPO_USUARIO", nullable = false, precision = 1, scale = 0)
	public Integer getBTipoUsuario() {
		return this.BTipoUsuario;
	}

	public void setBTipoUsuario(Integer BTipoUsuario) {
		this.BTipoUsuario = BTipoUsuario;
	}

	@Column(name = "S_TITULO", length = 100)
	public String getSTitulo() {
		return this.STitulo;
	}

	public void setSTitulo(String STitulo) {
		this.STitulo = STitulo;
	}

	@Column(name = "B_ESTADO", nullable = false, precision = 1, scale = 0)
	public Integer getBEstado() {
		return this.BEstado;
	}

	public void setBEstado(Integer BEstado) {
		this.BEstado = BEstado;
	}

	@Column(name = "S_MENSAJE_TRAZABILIDAD", nullable = false, length = 150)
	public String getSMensajeTrazabilidad() {
		return this.SMensajeTrazabilidad;
	}

	public void setSMensajeTrazabilidad(String SMensajeTrazabilidad) {
		this.SMensajeTrazabilidad = SMensajeTrazabilidad;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbTramiteWebDet")
	public Set<DeTrazabilidad> getDeTrazabilidads() {
		return this.deTrazabilidads;
	}

	public void setDeTrazabilidads(Set<DeTrazabilidad> deTrazabilidads) {
		this.deTrazabilidads = deTrazabilidads;
	}

	@Override
	public String toString() {
		return "TbTramiteWebDet [ITramiteWebDet=" + ITramiteWebDet
				+ ", tbTramiteWeb=" + tbTramiteWeb + ", SLogTrazabilidad="
				+ SLogTrazabilidad + ", fhIngreso=" + fhIngreso + ", CUsuario="
				+ CUsuario + ", BActivo=" + BActivo + ", BTipoUsuario="
				+ BTipoUsuario + ", STitulo=" + STitulo + ", BEstado="
				+ BEstado + ", SMensajeTrazabilidad=" + SMensajeTrazabilidad
				+ "]";
	}
	
	
}
