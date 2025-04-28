package sv.global.colas.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_FORMULARIOS", schema = "CATALOGOS", uniqueConstraints = {})
public class TbFormularios implements java.io.Serializable {

	private static final long serialVersionUID = -6465345318521182272L;

	@Id
	@Column(name = "C_FORMULARIO", unique = true, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)
	@NotNull
	private Integer cformulario;

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "C_TIPO_FORMULARIO", unique = false, nullable = false, insertable = true, updatable = true)
	private TbTipoFormulario tbTipoFormulario;

	@Column(name = "D_FORMULARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 80)
	@NotNull
	private String dformulario;

	@Column(name = "S_SIGLAS", unique = true, nullable = false, insertable = true, updatable = true, length = 4)
	@NotNull
	private String ssiglas;

	@Column(name = "C_USUARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	@NotNull
	private String cusuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_INGRESO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	@NotNull
	private Date fingreso;
        
        @Column(name = "C_ESTADO_WEB", unique = false, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)
        @NotNull
        private Integer cestadoweb;
        
        @Column(name = "B_ESTADO_WEB", unique = false, nullable = false, insertable = true, updatable = true, precision = 1, scale = 0)
        @NotNull
        private Integer bestadoweb;
        

	@Column(name = "B_PLANILLA", unique = false, nullable = false, insertable = true, updatable = true, precision = 1, scale = 0)
	@NotNull
	private Integer bplanilla;                  
        
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "tbFormularios")
	private Set<TbFormulariosSecciones> tbFormulariosSeccioneses = new HashSet<TbFormulariosSecciones>(
			0);

	

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "tbFormularios")
	private Set<TbFormulariosVersiones> tbFormulariosVersioneses = new HashSet<TbFormulariosVersiones>(
			0);
	
	public static final int IVA_NO_DOMICILIADO_MODELO_MANDAMIENTO = 89;

	public static final int IVA = 3;
	
	public static final int RENTA = 4;
	
	public static final int RENTA_MANDAMIENTO_INGRESO = 8;
	
	public static final int BOLETA_PAGOS = 45;
	
	public static final int RESOLUCIONES_TASACION_MULTA = 19;
	
	public static final int RUC = 1;
	
	public static final int ACTUALIZACION_DIRECCION = 75;
	
	public static final int RECIBO_UNICO_INGRESO = 18;
	
	public static final int INGRESO_RESOLUCION_FIANZA =  34;
	
	public static final int ESPECIFICOS_AD_VALOREM = 28;
	
	public static final int PAGO_A_CUENTA = 5;
	
	public static final int RENTECIONES = 5;

	public TbFormularios() {
	}
	public TbFormularios(Integer cformulario) {
		this.cformulario = cformulario;
	}

	public TbFormularios(Integer cformulario, TbTipoFormulario tbTipoFormulario,
			String dformulario, String ssiglas, Integer bplanilla,Integer cestadoweb, Integer bestadoweb) {
		this.cformulario = cformulario;
		this.tbTipoFormulario = tbTipoFormulario;
		this.dformulario = dformulario;
		this.ssiglas = ssiglas;
		this.bplanilla = bplanilla;
                this.cestadoweb= cestadoweb;
                this.bestadoweb=bestadoweb;
	}

	public TbFormularios(Integer cformulario, TbTipoFormulario tbTipoFormulario,
			String dformulario, String ssiglas, String cusuario, Date fingreso,
			Integer bplanilla,Integer cestadoweb,Integer bestadoweb,
			Set<TbFormulariosSecciones> tbFormulariosSeccioneses,
			Set<TbFormulariosVersiones> tbFormulariosVersioneses) {
		this.cformulario = cformulario;
		this.tbTipoFormulario = tbTipoFormulario;
		this.dformulario = dformulario;
		this.ssiglas = ssiglas;
		this.cusuario = cusuario;
		this.fingreso = fingreso;
		this.bplanilla = bplanilla;
                this.cestadoweb=cestadoweb;
                this.bestadoweb=bestadoweb;
		this.tbFormulariosSeccioneses = tbFormulariosSeccioneses;		
		this.tbFormulariosVersioneses = tbFormulariosVersioneses;
	}

	public Integer getCformulario() {
		return this.cformulario;
	}

	public void setCformulario(Integer cformulario) {
		this.cformulario = cformulario;
	}

	
	public TbTipoFormulario getTbTipoFormulario() {
		return this.tbTipoFormulario;
	}

	public void setTbTipoFormulario(TbTipoFormulario tbTipoFormulario) {
		this.tbTipoFormulario = tbTipoFormulario;
	}

	
	public String getDformulario() {
		return this.dformulario;
	}

	public void setDformulario(String dformulario) {
		this.dformulario = dformulario;
	}

	
	public String getSsiglas() {
		return this.ssiglas;
	}

	public void setSsiglas(String ssiglas) {
		this.ssiglas = ssiglas;
	}

	
	public String getCusuario() {
		return this.cusuario;
	}

	public void setCusuario(String cusuario) {
		this.cusuario = cusuario;
	}

	
	public Date getFingreso() {
		return this.fingreso;
	}

	public void setFingreso(Date fingreso) {
		this.fingreso = fingreso;
	}

	
	public Integer getBplanilla() {
		return this.bplanilla;
	}

	public void setBplanilla(Integer bplanilla) {
		this.bplanilla = bplanilla;
	}
	
	public Set<TbFormulariosSecciones> getTbFormulariosSeccioneses() {
		return this.tbFormulariosSeccioneses;
	}

	public void setTbFormulariosSeccioneses(
			Set<TbFormulariosSecciones> tbFormulariosSeccioneses) {
		this.tbFormulariosSeccioneses = tbFormulariosSeccioneses;
	}
	

	public Set<TbFormulariosVersiones> getTbFormulariosVersioneses() {
		return this.tbFormulariosVersioneses;
	}

	public void setTbFormulariosVersioneses(
			Set<TbFormulariosVersiones> tbFormulariosVersioneses) {
		this.tbFormulariosVersioneses = tbFormulariosVersioneses;
	}

    /**
     * @return the cestadoweb
     */
    public Integer getCestadoweb() {
        return cestadoweb;
    }

    /**
     * @param cestadoweb the cestadoweb to set
     */
    public void setCestadoweb(Integer cestadoweb) {
        this.cestadoweb = cestadoweb;
    }

    /**
     * @return the bestadoweb
     */
    public Integer getBestadoweb() {
        return bestadoweb;
    }

    /**
     * @param bestadoweb the bestadoweb to set
     */
    public void setBestadoweb(Integer bestadoweb) {
        this.bestadoweb = bestadoweb;
    }

}
