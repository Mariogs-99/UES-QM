package sv.global.colas.entities;

// Generated Dec 12, 2007 3:08:52 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TbTramite generated by hbm2java
 */
@Entity
@Table(name = "TB_TRAMITE", schema = "CATALOGOS")
public class TbTramite implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TbTramiteId id;
	private TbTipoGestion tbTipoGestion;
	private String cusuario;
	private String dtramite;
	private String stramite;
	private Date fhingreso;
	private Boolean bstatus;
	
	public TbTramite() {
	}

	public TbTramite(TbTramiteId id, TbTipoGestion tbTipoGestion,
			String cusuario, String dtramite, Date fhingreso, Boolean bstatus) {
		this.id = id;
		this.tbTipoGestion = tbTipoGestion;
		this.cusuario = cusuario;
		this.dtramite = dtramite;
		this.fhingreso = fhingreso;
		this.bstatus = bstatus;
	}

	public TbTramite(TbTramiteId id, TbTipoGestion tbTipoGestion,
			String cusuario, String dtramite, String stramite, Date fhingreso,
			Boolean bstatus) {
		this.id = id;
		this.tbTipoGestion = tbTipoGestion;
		this.cusuario = cusuario;
		this.dtramite = dtramite;
		this.stramite = stramite;
		this.fhingreso = fhingreso;
		this.bstatus = bstatus;
		
	}

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "cgestion", column = @Column(name = "C_GESTION", nullable = false, length = 1)),
			@AttributeOverride(name = "ctramite", column = @Column(name = "C_TRAMITE", nullable = false, length = 3)) })
	public TbTramiteId getId() {
		return this.id;
	}

	public void setId(TbTramiteId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "C_GESTION", nullable = false, insertable = false, updatable = false)
	public TbTipoGestion getTbTipoGestion() {
		return this.tbTipoGestion;
	}

	public void setTbTipoGestion(TbTipoGestion tbTipoGestion) {
		this.tbTipoGestion = tbTipoGestion;
	}

	@Column(name = "C_USUARIO", nullable = false, length = 30)
	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	@Column(name = "D_TRAMITE", nullable = false, length = 50)
	public String getDtramite() {
		return this.dtramite;
	}

	public void setDtramite(String dtramite) {
		this.dtramite = dtramite;
	}

	@Column(name = "S_TRAMITE", length = 15)
	public String getStramite() {
		return this.stramite;
	}

	public void setStramite(String stramite) {
		this.stramite = stramite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_INGRESO", nullable = false, length = 7)
	public Date getFhingreso() {
		return this.fhingreso;
	}

	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}

	@Column(name = "B_STATUS", nullable = false, precision = 1, scale = 0)
	public Boolean getBstatus() {
		return this.bstatus;
	}

	public void setBstatus(Boolean bstatus) {
		this.bstatus = bstatus;
	}

	


	

}
