/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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
@Table(name = "GC_RESPUESTAS", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcRespuestas.findAll", query = "SELECT g FROM GcRespuestas g")})
public class GcRespuestas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_RESPUESTA_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeRespuestas")
    //@SequenceGenerator(name = "SeqGeRespuestas", sequenceName = "SEQ_GC_RESPUESTAS")
    private Long nRespuestaId; 
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "S_RESPUESTA", nullable = false, length = 128)
    private String sRespuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ESCALA", nullable = false)
    private BigInteger nEscala;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nRespuestaId")
    private List<GcPreguntasRespuestas> gcPreguntasRespuestasList= new ArrayList<GcPreguntasRespuestas>();
    @JoinColumn(name = "N_PREGUNTA_ID", referencedColumnName = "N_PREGUNTA_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcPreguntas nPreguntaId;

    public GcRespuestas() {
    }

    public GcRespuestas(Long nRespuestaId) {
        this.nRespuestaId = nRespuestaId;
    }

    public GcRespuestas(Long nRespuestaId, String sRespuesta, BigInteger nEscala, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia) {
        this.nRespuestaId = nRespuestaId;
        this.sRespuesta = sRespuesta;
        this.nEscala = nEscala;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
    }

    public Long getNRespuestaId() {
        return nRespuestaId;
    }

    public void setNRespuestaId(Long nRespuestaId) {
        this.nRespuestaId = nRespuestaId;
    }

    public String getSRespuesta() {
        return sRespuesta;
    }

    public void setSRespuesta(String sRespuesta) {
        this.sRespuesta = sRespuesta;
    }

    public BigInteger getNEscala() {
        return nEscala;
    }

    public void setNEscala(BigInteger nEscala) {
        this.nEscala = nEscala;
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

    public List<GcPreguntasRespuestas> getGcPreguntasRespuestasList() {
    	return gcPreguntasRespuestasList;
    }

    public void setGcPreguntasRespuestasList(List<GcPreguntasRespuestas> gcPreguntasRespuestasList) {
        this.gcPreguntasRespuestasList = gcPreguntasRespuestasList;
    }

    public GcPreguntas getNPreguntaId() {
        return nPreguntaId;
    }

    public void setNPreguntaId(GcPreguntas nPreguntaId) {
        this.nPreguntaId = nPreguntaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nRespuestaId != null ? nRespuestaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcRespuestas)) {
            return false;
        }
        GcRespuestas other = (GcRespuestas) object;
        if ((this.nRespuestaId == null && other.nRespuestaId != null) || (this.nRespuestaId != null && !this.nRespuestaId.equals(other.nRespuestaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcRespuestas[ nRespuestaId=" + nRespuestaId + " ]";
    }
    
}
