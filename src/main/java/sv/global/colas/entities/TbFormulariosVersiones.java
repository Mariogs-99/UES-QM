package sv.global.colas.entities;

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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TB_FORMULARIOS_VERSIONES", schema = "CATALOGOS", uniqueConstraints = {})
public class TbFormulariosVersiones implements java.io.Serializable {

    @Column(name = "F_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fModificacion;
    @Column(name = "F_PUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fPublicacion;
    @Column(name = "B_ESTADO_WEB")
    private Integer bEstadoWeb;
    
    private static final long serialVersionUID = -3935726651289803257L;
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "cformulario", column =
                @Column(name = "C_FORMULARIO", unique = false, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)),
        @AttributeOverride(name = "cversion", column =
                @Column(name = "C_VERSION", unique = false, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0))})
    @NotNull
    @Valid
    private TbFormulariosVersionesId id;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_FORMULARIO", unique = false, nullable = false, insertable = false, updatable = false)
    @NotNull
    @Valid
    private TbFormularios tbFormularios;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "C_ESTADO_WEB", unique = false, nullable = false, insertable = true, updatable = true)
    @Valid
    private TbFormulariosEstadosWeb cEstadoWeb;
    @Temporal(TemporalType.DATE)
    @Column(name = "FI_VERSION", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
    @NotNull
    private Date fiversion;
    @Temporal(TemporalType.DATE)
    @Column(name = "FF_VERSION", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
    private Date ffversion;
    @Column(name = "S_VISOR", unique = false, nullable = false, insertable = true, updatable = true, length = 4000)
    @NotNull
    private String svisor;
    @Column(name = "C_USUARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
    @NotNull
    private String cusuario;
    @Temporal(TemporalType.DATE)
    @Column(name = "F_INGRESO", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
    @NotNull
    private Date fingreso;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "tbFormulariosVersiones")
    private Set<TbFormulariosCampos> tbFormulariosCamposes = new HashSet<TbFormulariosCampos>(
            0);
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "tbFormulariosVersiones")
    private Set<EdDeclaraciones> edDeclaracioneses = new HashSet<EdDeclaraciones>(
            0);
    
    public TbFormulariosVersiones() {
    }

    public TbFormulariosVersiones(Integer formulario, Integer version) {
        this.tbFormularios = new TbFormularios(formulario);
        this.id = new TbFormulariosVersionesId(formulario, version);
    }

    public TbFormulariosVersiones(TbFormulariosVersionesId id,
            TbFormularios tbFormularios, Date fiversion, String svisor,
            String cusuario, Date fingreso) {
        this.id = id;
        this.tbFormularios = tbFormularios;
        this.fiversion = fiversion;
        this.svisor = svisor;
        this.cusuario = cusuario;
        this.fingreso = fingreso;
    }

    public TbFormulariosVersiones(TbFormulariosVersionesId id,
            TbFormularios tbFormularios, Date fiversion, Date ffversion,
            String svisor, String cusuario, Date fingreso,
            Set<TbFormulariosCampos> tbFormulariosCamposes,           
            Set<EdDeclaraciones> edDeclaracioneses) {
        this.id = id;
        this.tbFormularios = tbFormularios;
        this.fiversion = fiversion;
        this.ffversion = ffversion;
        this.svisor = svisor;
        this.cusuario = cusuario;
        this.fingreso = fingreso;
        this.tbFormulariosCamposes = tbFormulariosCamposes;        
        this.edDeclaracioneses = edDeclaracioneses;        
    }

    public TbFormulariosVersionesId getId() {
        return this.id;
    }

    public void setId(TbFormulariosVersionesId id) {
        this.id = id;
    }

    public TbFormularios getTbFormularios() {
        return this.tbFormularios;
    }

    public void setTbFormularios(TbFormularios tbFormularios) {
        this.tbFormularios = tbFormularios;
    }

    public TbFormulariosEstadosWeb getCEstadoWeb() {
        return this.cEstadoWeb;
    }

    public void setCEstadoWeb(TbFormulariosEstadosWeb cEstadoWeb) {
        this.cEstadoWeb = cEstadoWeb;
    }

    public Date getFiversion() {
        return this.fiversion;
    }

    public void setFiversion(Date fiversion) {
        this.fiversion = fiversion;
    }

    public Date getFfversion() {
        return this.ffversion;
    }

    public void setFfversion(Date ffversion) {
        this.ffversion = ffversion;
    }

    public String getSvisor() {
        return this.svisor;
    }

    public void setSvisor(String svisor) {
        this.svisor = svisor;
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

    public Set<TbFormulariosCampos> getTbFormulariosCamposes() {
        return this.tbFormulariosCamposes;
    }

    public void setTbFormulariosCamposes(
            Set<TbFormulariosCampos> tbFormulariosCamposes) {
        this.tbFormulariosCamposes = tbFormulariosCamposes;
    }

   
    public Set<EdDeclaraciones> getEdDeclaracioneses() {
        return this.edDeclaracioneses;
    }

    public void setEdDeclaracioneses(Set<EdDeclaraciones> edDeclaracioneses) {
        this.edDeclaracioneses = edDeclaracioneses;
    }

   
    public Date getFModificacion() {
        return fModificacion;
    }

    public void setFModificacion(Date fModificacion) {
        this.fModificacion = fModificacion;
    }

    public Date getFPublicacion() {
        return fPublicacion;
    }

    public void setFPublicacion(Date fPublicacion) {
        this.fPublicacion = fPublicacion;
    }

    public Integer getBEstadoWeb() {
        return bEstadoWeb;
    }

    public void setBEstadoWeb(Integer bEstadoWeb) {
        this.bEstadoWeb = bEstadoWeb;
    }

   
}
