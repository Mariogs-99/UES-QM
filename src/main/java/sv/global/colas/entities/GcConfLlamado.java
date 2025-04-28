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
@Table(name = "GC_CONF_LLAMADO", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcConfLlamado.findAll", query = "SELECT g FROM GcConfLlamado g")})
public class GcConfLlamado implements Serializable {
    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_NUM_LLAMADAS", nullable = false)
    private BigInteger nNumLlamadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_INTERVALO_LLAMADA", nullable = false)
    private BigInteger nIntervaloLlamada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "S_MENSAJE", nullable = false, length = 1024)
    private String sMensaje;
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
    @Size(max = 256)
    @Column(name = "S_DESCRIPCION", length = 256)
    private String sDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_CONFLLAMADO_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGecllamado")
    //@SequenceGenerator(name = "SeqGecllamado", sequenceName = "SEQ_GC_CONF_LLAMADO")
    private Long nConfllamadoId;

    public GcConfLlamado() {
    }

    public GcConfLlamado(Long nConfllamadoId) {
        this.nConfllamadoId = nConfllamadoId;
    }

    public GcConfLlamado(Long nConfllamadoId, String cUnidadRecep, BigInteger nNumLlamadas, BigInteger nIntervaloLlamada, short bActiva, String sMensaje, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia) {
        this.nConfllamadoId = nConfllamadoId;
        this.nNumLlamadas = nNumLlamadas;
        this.nIntervaloLlamada = nIntervaloLlamada;
        this.bActiva = bActiva;
        this.sMensaje = sMensaje;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public BigInteger getNNumLlamadas() {
        return nNumLlamadas;
    }

    public void setNNumLlamadas(BigInteger nNumLlamadas) {
        this.nNumLlamadas = nNumLlamadas;
    }

    public BigInteger getNIntervaloLlamada() {
        return nIntervaloLlamada;
    }

    public void setNIntervaloLlamada(BigInteger nIntervaloLlamada) {
        this.nIntervaloLlamada = nIntervaloLlamada;
    }

    public short getBActiva() {
        return bActiva;
    }

    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getSMensaje() {
        return sMensaje;
    }

    public void setSMensaje(String sMensaje) {
        this.sMensaje = sMensaje;
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

    public String getSDescripcion() {
        return sDescripcion;
    }

    public void setSDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public Long getNConfllamadoId() {
        return nConfllamadoId;
    }

    public void setNConfllamadoId(Long nConfllamadoId) {
        this.nConfllamadoId = nConfllamadoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nConfllamadoId != null ? nConfllamadoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcConfLlamado)) {
            return false;
        }
        GcConfLlamado other = (GcConfLlamado) object;
        if ((this.nConfllamadoId == null && other.nConfllamadoId != null) || (this.nConfllamadoId != null && !this.nConfllamadoId.equals(other.nConfllamadoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcConfLlamado[ nConfllamadoId=" + nConfllamadoId + " ]";
    }
    
}
