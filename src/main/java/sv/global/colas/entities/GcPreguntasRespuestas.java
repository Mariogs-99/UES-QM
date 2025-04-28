/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author Owner
 */
@Entity
@Table(name = "GC_PREGUNTAS_RESPUESTAS", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcPreguntasRespuestas.findAll", query = "SELECT g FROM GcPreguntasRespuestas g")})
public class GcPreguntasRespuestas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_PREGUNTA_RESPUESTA_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGcPreguntasRespuestas")
    //@SequenceGenerator(name = "SeqGcPreguntasRespuestas", sequenceName = "SEQ_GC_PREGUNTAS_RESPUESTAS")
    private Long nPreguntaRespuestaId;
    @Column(name = "FH_RESPONDIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhRespondio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "C_USUARIO", nullable = false, length = 256)
    private String cUsuario;
    @JoinColumn(name = "N_RESPUESTA_ID", referencedColumnName = "N_RESPUESTA_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcRespuestas nRespuestaId;

    public GcPreguntasRespuestas() {
    }

    public GcPreguntasRespuestas(Long nPreguntaRespuestaId) {
        this.nPreguntaRespuestaId = nPreguntaRespuestaId;
    }

    public GcPreguntasRespuestas(Long nPreguntaRespuestaId, String cUsuario) {
        this.nPreguntaRespuestaId = nPreguntaRespuestaId;
        this.cUsuario = cUsuario;
    }

    public Long getNPreguntaRespuestaId() {
        return nPreguntaRespuestaId;
    }

    public void setNPreguntaRespuestaId(Long nPreguntaRespuestaId) {
        this.nPreguntaRespuestaId = nPreguntaRespuestaId;
    }

    public Date getFhRespondio() {
        return fhRespondio;
    }

    public void setFhRespondio(Date fhRespondio) {
        this.fhRespondio = fhRespondio;
    }

    public String getCUsuario() {
        return cUsuario;
    }

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public GcRespuestas getNRespuestaId() {
        return nRespuestaId;
    }

    public void setNRespuestaId(GcRespuestas nRespuestaId) {
        this.nRespuestaId = nRespuestaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nPreguntaRespuestaId != null ? nPreguntaRespuestaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcPreguntasRespuestas)) {
            return false;
        }
        GcPreguntasRespuestas other = (GcPreguntasRespuestas) object;
        if ((this.nPreguntaRespuestaId == null && other.nPreguntaRespuestaId != null) || (this.nPreguntaRespuestaId != null && !this.nPreguntaRespuestaId.equals(other.nPreguntaRespuestaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcPreguntasRespuestas[ nPreguntaRespuestaId=" + nPreguntaRespuestaId + " ]";
    }
    
}
