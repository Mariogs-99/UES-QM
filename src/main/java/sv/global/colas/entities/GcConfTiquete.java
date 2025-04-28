package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author agustin
 */
@Entity
@Table(name = "GC_CONF_TIQUETE")
@NamedQueries({
    @NamedQuery(name = "GcConfTiquete.findAll", query = "SELECT g FROM GcConfTiquete g")})
public class GcConfTiquete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "C_UNIDAD_RECEP")
    private String cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA")
    private short bActiva;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "X_CONTENIDO")
    private String xContenido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C_USUARIO_CREA")
    private String cUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C_USUARIO_MODI")
    private String cUsuarioModi;
    @Column(name = "FF_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiVigencia;
    @Column(name = "F_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fModifica;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_CONF_TIQ_ID")
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeConTiquete")
    //@SequenceGenerator(name = "SeqGeConTiquete", sequenceName = "SEQ_GC_CONF_TIQUETE")
    private Long nConfTiqId;
    @Column(name = "N_VERSION")
    private long nVersion;
    @Lob
    @Column(name = "X_IMAGEN")
    private Serializable xImagen;

    public GcConfTiquete() {
    }

    public GcConfTiquete(long nConfTiqId) {
        this.nConfTiqId = nConfTiqId;
    }

    public GcConfTiquete(long nConfTiqId, String cUnidadRecep, short bActiva, String xContenido, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia) {
        this.nConfTiqId = nConfTiqId;
        this.cUnidadRecep = cUnidadRecep;
        this.bActiva = bActiva;
        this.xContenido = xContenido;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
    }

    public String getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(String cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public short getBActiva() {
        return bActiva;
    }

    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getXContenido() {
        return xContenido;
    }

    public void setXContenido(String xContenido) {
        this.xContenido = xContenido;
    }

    public String getCUsuarioCrea() {
        return cUsuarioCrea;
    }

    public void setCUsuarioCrea(String cUsuarioCrea) {
        this.cUsuarioCrea = cUsuarioCrea;
    }

    public String getCUsuarioModi() {
        return cUsuarioModi;
    }

    public void setCUsuarioModi(String cUsuarioModi) {
        this.cUsuarioModi = cUsuarioModi;
    }

    public Date getFfVigencia() {
        return ffVigencia;
    }

    public void setFfVigencia(Date ffVigencia) {
        this.ffVigencia = ffVigencia;
    }

    public Date getFiVigencia() {
        return fiVigencia;
    }

    public void setFiVigencia(Date fiVigencia) {
        this.fiVigencia = fiVigencia;
    }

    public Date getFModifica() {
        return fModifica;
    }

    public void setFModifica(Date fModifica) {
        this.fModifica = fModifica;
    }

    public Long getNConfTiqId() {
        return nConfTiqId;
    }

    public void setNConfTiqId(Long nConfTiqId) {
        this.nConfTiqId = nConfTiqId;
    }

    public long getNVersion() {
        return nVersion;
    }

    public void setNVersion(long nVersion) {
        this.nVersion = nVersion;
    }

    public Serializable getXImagen() {
        return xImagen;
    }

    public void setXImagen(Serializable xImagen) {
        this.xImagen = xImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nConfTiqId != null ? nConfTiqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcConfTiquete)) {
            return false;
        }
        GcConfTiquete other = (GcConfTiquete) object;
        if ((this.nConfTiqId == null && other.nConfTiqId != null) || (this.nConfTiqId != null && !this.nConfTiqId.equals(other.nConfTiqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcConfTiquete[ nConfTiqId=" + nConfTiqId + " ]";
    }
    
}