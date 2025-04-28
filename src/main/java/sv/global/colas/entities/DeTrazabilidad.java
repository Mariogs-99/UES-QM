package sv.global.colas.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@SequenceGenerator(name = "trazSequence", sequenceName = "SIIT.SEQ_DE_TRAZABILIDAD")
@Table(name = "DE_TRAZABILIDAD", schema = "DECLARACIONES")
public class DeTrazabilidad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ITrazabilidad;
	private TbTramiteWebDet tbTramiteWebDet;
	private String CUsuario;
	private String nitTitular;
	private Date fhIngreso;
	private String SIp;
	private String SReferencia;
	private String nitTramite;

	public DeTrazabilidad() {

	}

	@Id
	//@GeneratedValue(generator = "trazSequence")
	@Column(name = "I_TRAZABILIDAD", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getITrazabilidad() {
		return this.ITrazabilidad;
	}

	public void setITrazabilidad(Long ITrazabilidad) {
		this.ITrazabilidad = ITrazabilidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "C_TRAMITE_WEB_DET")
	public TbTramiteWebDet getTbTramiteWebDet() {
		return this.tbTramiteWebDet;
	}

	public void setTbTramiteWebDet(TbTramiteWebDet tbTramiteWebDet) {
		this.tbTramiteWebDet = tbTramiteWebDet;
	}

	@Column(name = "C_USUARIO", nullable = false, length = 60)
	public String getCUsuario() {
		return this.CUsuario;
	}

	public void setCUsuario(String CUsuario) {
		this.CUsuario = CUsuario;
	}

	@Column(name = "NIT_TITULAR", length = 14)
	public String getNitTitular() {
		return this.nitTitular;
	}

	public void setNitTitular(String nitTitular) {
		this.nitTitular = nitTitular;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_INGRESO", nullable = false)
	public Date getFhIngreso() {
		return this.fhIngreso;
	}

	public void setFhIngreso(Date fhIngreso) {
		this.fhIngreso = fhIngreso;
	}

	@Column(name = "S_IP", nullable = false, length = 39)
	public String getSIp() {
		return this.SIp;
	}

	public void setSIp(String SIp) {
		this.SIp = SIp;
	}

	@Column(name = "S_REFERENCIA", length = 3000)
	public String getSReferencia() {
		return this.SReferencia;
	}

	public void setSReferencia(String SReferencia) {
		this.SReferencia = SReferencia;
	}

	@Column(name = "NIT_TRAMITE", nullable = false, length = 14)
	public String getNitTramite() {
		return this.nitTramite;
	}

	public void setNitTramite(String nitTramite) {
		this.nitTramite = nitTramite;
	}

	@Override
	public String toString() {
		return "DeTrazabilidad [ITrazabilidad=" + ITrazabilidad
				+ ", tbTramiteWebDet=" + tbTramiteWebDet + ", CUsuario="
				+ CUsuario + ", nitTitular=" + nitTitular + ", fhIngreso="
				+ fhIngreso + ", SIp=" + SIp + ", SReferencia=" + SReferencia
				+ ", nitTramite=" + nitTramite + "]";
	}

	
}
