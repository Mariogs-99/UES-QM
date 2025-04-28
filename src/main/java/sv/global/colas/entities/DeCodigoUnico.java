package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "sv.gob.mh.dgii.cunico.model.DeCodigoUnico")
@Table(name = "DE_CODIGO_UNICO")
public class DeCodigoUnico implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cCodigoUnico;
	private String nit;
	private String cUsuario;
	private Date fhIngreso = Calendar.getInstance().getTime();
	private Date fhVencimiento;
	private String sObservacion1;
	private String sObservacion2;
	private String sObservacion3;
	private String sTitulo;
	private DeCodigoUnicoDet iServicioDet;

	public DeCodigoUnico() {
	}

	public DeCodigoUnico(String cCodigoUnico) {
		this.cCodigoUnico = cCodigoUnico;
	}

	public DeCodigoUnico(String cCodigoUnico, String nit, String cUsuario,
			Date fhIngreso) {
		this.cCodigoUnico = cCodigoUnico;
		this.nit = nit;
		this.cUsuario = cUsuario;
		this.fhIngreso = fhIngreso;
	}

	@Id	
	@Column(name = "C_CODIGO_UNICO")
	public String getcCodigoUnico() {
		return cCodigoUnico;
	}

	public void setcCodigoUnico(String cCodigoUnico) {
		this.cCodigoUnico = cCodigoUnico;
	}
	
	@Column(name = "NIT")
	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
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

	@Column(name = "FH_VENCIMIENTO")
	@Temporal(TemporalType.DATE)
	public Date getFhVencimiento() {
		return fhVencimiento;
	}

	public void setFhVencimiento(Date fhVencimiento) {
		this.fhVencimiento = fhVencimiento;
	}

	@Column(name = "S_OBSERVACION1")
	public String getSObservacion1() {
		return sObservacion1;
	}

	public void setSObservacion1(String sObservacion1) {
		this.sObservacion1 = sObservacion1;
	}

	@Column(name = "S_OBSERVACION2")
	public String getSObservacion2() {
		return sObservacion2;
	}

	public void setSObservacion2(String sObservacion2) {
		this.sObservacion2 = sObservacion2;
	}

	@Column(name = "S_OBSERVACION3")
	public String getSObservacion3() {
		return sObservacion3;
	}

	public void setSObservacion3(String sObservacion3) {
		this.sObservacion3 = sObservacion3;
	}

	@Column(name = "S_TITULO")
	public String getSTitulo() {
		return sTitulo;
	}

	public void setSTitulo(String sTitulo) {
		this.sTitulo = sTitulo;
	}

	@JoinColumn(name = "I_SERVICIO_DET", referencedColumnName = "I_SERVICIO_DET")
	@ManyToOne(optional = false)
	public DeCodigoUnicoDet getiServicioDet() {
		return iServicioDet;
	}

	public void setiServicioDet(DeCodigoUnicoDet iServicioDet) {
		this.iServicioDet = iServicioDet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cCodigoUnico != null ? cCodigoUnico.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof DeCodigoUnico)) {
			return false;
		}
		DeCodigoUnico other = (DeCodigoUnico) object;
		if ((this.cCodigoUnico == null && other.cCodigoUnico != null)
				|| (this.cCodigoUnico != null && !this.cCodigoUnico
						.equals(other.cCodigoUnico))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "sv.gob.mh.dgii.model.DeCodigoUnico[ cCodigoUnico="
				+ cCodigoUnico + " ]";
	}

}
