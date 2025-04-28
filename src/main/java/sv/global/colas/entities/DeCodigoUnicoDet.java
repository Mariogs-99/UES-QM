package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "sv.gob.mh.dgii.cunico.model.DeCodigoUnicoDet")
@Table(name = "DE_CODIGO_UNICO_DET", schema = "DECLARACIONES")
public class DeCodigoUnicoDet implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long iServicioDet;
	private String sDetalle;
	private String sCampo1;
	private String sCampo2;
	private String sCampo3;
	private String cUsuario;
	private Date fhIngreso = Calendar.getInstance().getTime();
	private Long iTramiteWebDet;

	@Column(name = "I_TRAMITE_WEB_DET")
	public Long getiTramiteWebDet() {
		return iTramiteWebDet;
	}

	public void setiTramiteWebDet(Long iTramiteWebDet) {
		this.iTramiteWebDet = iTramiteWebDet;
	}

	public DeCodigoUnicoDet() {
	}

	public DeCodigoUnicoDet(Long iServicioDet) {
		this.iServicioDet = iServicioDet;
	}

	@Id	
	@Column(name = "I_SERVICIO_DET")
	public Long getIServicioDet() {
		return iServicioDet;
	}

	public void setIServicioDet(Long iServicioDet) {
		this.iServicioDet = iServicioDet;
	}
	
	@Column(name = "S_DETALLE")
	public String getSDetalle() {
		return sDetalle;
	}

	public void setSDetalle(String sDetalle) {
		this.sDetalle = sDetalle;
	}
	
	@Column(name = "S_CAMPO1")
	public String getSCampo1() {
		return sCampo1;
	}

	public void setSCampo1(String sCampo1) {
		this.sCampo1 = sCampo1;
	}
	
	@Column(name = "S_CAMPO2")
	public String getSCampo2() {
		return sCampo2;
	}

	public void setSCampo2(String sCampo2) {
		this.sCampo2 = sCampo2;
	}
	
	@Column(name = "S_CAMPO3")
	public String getSCampo3() {
		return sCampo3;
	}

	public void setSCampo3(String sCampo3) {
		this.sCampo3 = sCampo3;
	}
	
	@Column(name = "C_USUARIO")
	public String getCUsuario() {
		return cUsuario;
	}

	public void setCUsuario(String cUsuario) {
		this.cUsuario = cUsuario;
	}

	@Column(name = "FH_INGRESO")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFhIngreso() {
		return fhIngreso;
	}

	public void setFhIngreso(Date fhIngreso) {
		this.fhIngreso = fhIngreso;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (iServicioDet != null ? iServicioDet.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {		
		if (!(object instanceof DeCodigoUnicoDet)) {
			return false;
		}
		DeCodigoUnicoDet other = (DeCodigoUnicoDet) object;
		if ((this.iServicioDet == null && other.iServicioDet != null)
				|| (this.iServicioDet != null && !this.iServicioDet
						.equals(other.iServicioDet))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sv.gob.mh.dgii.cunico.model.DeCodigoUnicoDet[ iServicioDet="
				+ iServicioDet + " ]";
	}
}
