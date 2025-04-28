/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Owner
 */
@Entity
@Table(name = "GC_PREGUNTAS", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcPreguntas.findAll", query = "SELECT g FROM GcPreguntas g")})
public class GcPreguntas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_PREGUNTA_ID", nullable = false, precision = 22)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGePreguntas")
    //@SequenceGenerator(name = "SeqGePreguntas", sequenceName = "SEQ_GC_PREGUNTAS")
    private Long nPreguntaId;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "S_PREGUNTA", nullable = false, length = 256)
    private String sPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_PONDERACION", nullable = false)
    private BigInteger nPonderacion;
    @Size(max = 512)
    @Column(name = "D_PREGUNTA", length = 512)
    private String dPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C_USUARIO_CREA", nullable = false, length = 100)
    private String cUsuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "C_USUARIO_MODI", nullable = false, length = 100)
    private String cUsuarioModi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_VIGENCIA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiVigencia;
    @Column(name = "FF_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffVigencia;
    @Column(name = "F_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nPreguntaId")
    private List<GcRespuestas> gcRespuestasList;

    public GcPreguntas() {
    }

    public GcPreguntas(Long nPreguntaId) {
        this.nPreguntaId = nPreguntaId;
    }

    public GcPreguntas(Long nPreguntaId, String sPregunta, BigInteger nPonderacion, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia) {
        this.nPreguntaId = nPreguntaId;
        this.sPregunta = sPregunta;
        this.nPonderacion = nPonderacion;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
    }

    public Long getNPreguntaId() {
        return nPreguntaId;
    }

    public void setNPreguntaId(Long nPreguntaId) {
        this.nPreguntaId = nPreguntaId;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public String getSPregunta() {
        return sPregunta;
    }

    public void setSPregunta(String sPregunta) {
        this.sPregunta = sPregunta;
    }

    public BigInteger getNPonderacion() {
        return nPonderacion;
    }

    public void setNPonderacion(BigInteger nPonderacion) {
        this.nPonderacion = nPonderacion;
    }

    public String getDPregunta() {
        return dPregunta;
    }

    public void setDPregunta(String dPregunta) {
        this.dPregunta = dPregunta;
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

    public Date getFiVigencia() {
        return fiVigencia;
    }

    public void setFiVigencia(Date fiVigencia) {
        this.fiVigencia = fiVigencia;
    }

    public Date getFfVigencia() {
        return ffVigencia;
    }

    public void setFfVigencia(Date ffVigencia) {
        this.ffVigencia = ffVigencia;
    }

    public Date getFModifica() {
        return fModifica;
    }

    public void setFModifica(Date fModifica) {
        this.fModifica = fModifica;
    }

    public List<GcRespuestas> getGcRespuestasList() {
        return gcRespuestasList;
    }

    public void setGcRespuestasList(List<GcRespuestas> gcRespuestasList) {
        this.gcRespuestasList = gcRespuestasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nPreguntaId != null ? nPreguntaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcPreguntas)) {
            return false;
        }
        GcPreguntas other = (GcPreguntas) object;
        if ((this.nPreguntaId == null && other.nPreguntaId != null) || (this.nPreguntaId != null && !this.nPreguntaId.equals(other.nPreguntaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcPreguntas[ nPreguntaId=" + nPreguntaId + " ]";
    }
    
}
