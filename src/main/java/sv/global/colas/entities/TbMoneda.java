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
@Table(name = "TB_MONEDA", schema = "CATALOGOS", uniqueConstraints = {})
public class TbMoneda implements java.io.Serializable {

	private static final long serialVersionUID = -2251955463003500393L;
	
	public static final String DOLARES= "02";
	
	public static final String COLONES= "01";

	@Id
	@Column(name = "C_MONEDA", unique = true, nullable = false, insertable = true, updatable = true, length = 2)
	@NotNull
	private String cmoneda;

	@Column(name = "D_MONEDA", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	@NotNull
	private String dmoneda;

	@Column(name = "B_LOCAL", unique = false, nullable = false, insertable = true, updatable = true, precision = 1, scale = 0)
	@NotNull
	private Integer blocal;

	@Column(name = "C_SIMBOLO", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
	@NotNull
	private String csimbolo;

	@Column(name = "C_USUARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	@NotNull
	private String cusuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_INGRESO", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	@NotNull
	private Date fhingreso;

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tbMoneda")
	private Set<EdNotaAbono> edNotaAbonos = new HashSet<EdNotaAbono>(0);

	

	public TbMoneda() {
	}
	public TbMoneda(String cmoneda ) {
		this.cmoneda = cmoneda;
	}

	public TbMoneda(String cmoneda, String dmoneda, Integer blocal,
			String csimbolo, String cusuario, Date fhingreso) {
		this.cmoneda = cmoneda;
		this.dmoneda = dmoneda;
		this.blocal = blocal;
		this.csimbolo = csimbolo;
		this.cusuario = cusuario;
		this.fhingreso = fhingreso;
	}
	
	public TbMoneda(String cmoneda, String dmoneda, Integer blocal,
			String csimbolo, String cusuario, Date fhingreso,
			Set<EdNotaAbono> edNotaAbonos) {
		this.cmoneda = cmoneda;
		this.dmoneda = dmoneda;
		this.blocal = blocal;
		this.csimbolo = csimbolo;
		this.cusuario = cusuario;
		this.fhingreso = fhingreso;
		this.edNotaAbonos = edNotaAbonos;		
	}

	
	public String getCmoneda() {
		return this.cmoneda;
	}

	public void setCmoneda(String cmoneda) {
		this.cmoneda = cmoneda;
	}

	
	public String getDmoneda() {
		return this.dmoneda;
	}

	public void setDmoneda(String dmoneda) {
		this.dmoneda = dmoneda;
	}

	
	public Integer getBlocal() {
		return this.blocal;
	}

	public void setBlocal(Integer blocal) {
		this.blocal = blocal;
	}

	
	public String getCsimbolo() {
		return this.csimbolo;
	}

	public void setCsimbolo(String csimbolo) {
		this.csimbolo = csimbolo;
	}

	
	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	
	public Date getFhingreso() {
		return this.fhingreso;
	}

	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}
	
	public Set<EdNotaAbono> getEdNotaAbonos() {
		return this.edNotaAbonos;
	}

	public void setEdNotaAbonos(Set<EdNotaAbono> edNotaAbonos) {
		this.edNotaAbonos = edNotaAbonos;
	}

	
}
