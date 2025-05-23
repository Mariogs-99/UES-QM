package sv.global.colas.entities;

// Generated Dec 12, 2007 1:25:05 PM by Hibernate Tools 3.2.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RcTramite generated by hbm2java
 */
@Entity
@Table(name = "RC_TRAMITE", schema = "RUC")
public class RcTramite implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7898566854608457225L;
	private RcTramiteId id;
	private RcRuc rcRuc;
	private String cgestion;
	private TbAdmTrib tbAdmTrib;
	private TbUnidadRecep tbUnidadRecep;
	private TbTramite tbTramite;
	
	private String cusuario;
	private Date ftramite;
	private Date fhingreso;
	private String criesgoInsc;
	private String nnotaAbono;
	private String ntiquete;
        
        private String codigoUnico; 

	public RcTramite() {
	}

	public RcTramite(RcTramiteId id, RcRuc rcRuc, String cgestion,
			TbAdmTrib tbAdmTrib, TbUnidadRecep tbUnidadRecep, String cusuario,
			Date ftramite, Date fhingreso, String criesgoInsc) {
		this.id = id;
		this.rcRuc = rcRuc;
		this.cgestion = cgestion;
		this.tbAdmTrib = tbAdmTrib;
		this.tbUnidadRecep = tbUnidadRecep;
		this.cusuario = cusuario;
		this.ftramite = ftramite;
		this.fhingreso = fhingreso;
		this.criesgoInsc = criesgoInsc;
	}

	public RcTramite(RcTramiteId id, RcRuc rcRuc, String cgestion,
			TbAdmTrib tbAdmTrib, TbUnidadRecep tbUnidadRecep, String cusuario,
			Date ftramite, Date fhingreso, String criesgoInsc, String nnotaAbono, String ntiquete , String codigoUnico) {
		this.id = id;
		this.rcRuc = rcRuc;
		this.cgestion = cgestion;
		this.tbAdmTrib = tbAdmTrib;
		this.tbUnidadRecep = tbUnidadRecep;
		this.cusuario = cusuario;
		this.ftramite = ftramite;
		this.fhingreso = fhingreso;
		this.criesgoInsc = criesgoInsc;
		this.nnotaAbono = nnotaAbono;
		this.setNtiquete(ntiquete);
                this.codigoUnico = codigoUnico;
	}

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "nfolio", column = @Column(name = "N_FOLIO", nullable = false, length = 12)),
			@AttributeOverride(name = "ctramite", column = @Column(name = "C_TRAMITE", nullable = false, length = 3)),
			@AttributeOverride(name = "nit", column = @Column(name = "NIT", nullable = false, length = 14)) })
	public RcTramiteId getId() {
		return this.id;
	}

	public void setId(RcTramiteId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NIT", nullable = false, insertable = false, updatable = false)
	public RcRuc getRcRuc() {
		return this.rcRuc;
	}

	public void setRcRuc(RcRuc rcRuc) {
		this.rcRuc = rcRuc;
	}

	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumns({
		@JoinColumn(nullable=false, name="C_GESTION",updatable=false, insertable=false),
		@JoinColumn(nullable=false, name="C_TRAMITE",updatable=false, insertable=false)
	})
	public TbTramite getTbTramite() {
		return tbTramite;
	}

	public void setTbTramite(TbTramite tbTramite) {
		this.tbTramite = tbTramite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "C_ADM_TRIB", nullable = false)
	public TbAdmTrib getTbAdmTrib() {
		return this.tbAdmTrib;
	}

	public void setTbAdmTrib(TbAdmTrib tbAdmTrib) {
		this.tbAdmTrib = tbAdmTrib;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "C_UNIDAD_RECEP", nullable = false)
	public TbUnidadRecep getTbUnidadRecep() {
		return this.tbUnidadRecep;
	}

	public void setTbUnidadRecep(TbUnidadRecep tbUnidadRecep) {
		this.tbUnidadRecep = tbUnidadRecep;
	}

	@Column(name = "C_USUARIO", nullable = false, length = 30)
	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "F_TRAMITE", nullable = false, length = 7)
	public Date getFtramite() {
		return this.ftramite;
	}

	public void setFtramite(Date ftramite) {
		this.ftramite = ftramite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FH_INGRESO", nullable = false, length = 7)
	public Date getFhingreso() {
		return this.fhingreso;
	}

	public void setFhingreso(Date fhingreso) {
		this.fhingreso = fhingreso;
	}

	@Column(name = "C_RIESGO_INSC", nullable = false, length = 1)
	public String getCriesgoInsc() {
		return this.criesgoInsc;
	}

	public void setCriesgoInsc(String criesgoInsc) {
		this.criesgoInsc = criesgoInsc;
	}

	@Column(name = "N_NOTA_ABONO", length = 12)
	public String getNnotaAbono() {
		return this.nnotaAbono;
	}

	public void setNnotaAbono(String nnotaAbono) {
		this.nnotaAbono = nnotaAbono;
	}

	/**
	 * @return the cgestion
	 */
	@Column(name = "C_GESTION", nullable = false, length = 1)
	public String getCgestion() {
		return cgestion;
	}

	/**
	 * @param cgestion the cgestion to set
	 */
	public void setCgestion(String cgestion) {
		this.cgestion = cgestion;
	}

	
	@Column(name = "N_TIQUETE", length = 12)
	public String getNtiquete() {
		return ntiquete;
	}
	
	public void setNtiquete(String ntiquete) {
		this.ntiquete = ntiquete;
	}
        
        
       @Column(name = "C_CODIGO_UNICO", length = 12)
        public String getCodigoUnico() {
           return codigoUnico;
       }

        public void setCodigoUnico(String codigoUnico) {
           this.codigoUnico = codigoUnico;
       }

        
}
