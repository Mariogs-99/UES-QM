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

@Entity
@Table(name = "TB_TRAMITE_WEB", schema = "CATALOGOS")
public class TbTramiteWeb implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ITramiteWeb;
	private String DTramiteWeb;
	private Date fhIngreso;
	private String CUsuario;
	private Integer BActivo;
	private Integer BTipoUsuario;
	private Set<TbTramiteWebDet> tbTramiteWebDets = new HashSet<TbTramiteWebDet>(
			0);

	public TbTramiteWeb() {

	}

	@Id
	@Column(name = "I_TRAMITE_WEB", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getITramiteWeb() {
		return this.ITramiteWeb;
	}

	public void setITramiteWeb(Long ITramiteWeb) {
		this.ITramiteWeb = ITramiteWeb;
	}

	@Column(name = "D_TRAMITE_WEB", nullable = false, length = 150)
	public String getDTramiteWeb() {
		return this.DTramiteWeb;
	}

	public void setDTramiteWeb(String DTramiteWeb) {
		this.DTramiteWeb = DTramiteWeb;
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

	@Column(name = "B_TIPO_USUARIO", precision = 1, scale = 0)
	public Integer getBTipoUsuario() {
		return this.BTipoUsuario;
	}

	public void setBTipoUsuario(Integer BTipoUsuario) {
		this.BTipoUsuario = BTipoUsuario;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tbTramiteWeb")
	public Set<TbTramiteWebDet> getTbTramiteWebDets() {
		return this.tbTramiteWebDets;
	}

	public void setTbTramiteWebDets(Set<TbTramiteWebDet> tbTramiteWebDets) {
		this.tbTramiteWebDets = tbTramiteWebDets;
	}

	@Override
	public String toString() {
		return "TbTramiteWeb [ITramiteWeb=" + ITramiteWeb + ", DTramiteWeb="
				+ DTramiteWeb + ", fhIngreso=" + fhIngreso + ", CUsuario="
				+ CUsuario + ", BActivo=" + BActivo + ", BTipoUsuario="
				+ BTipoUsuario + "]";
	}
	
	

}
