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
@Table(name = "GC_CONF_TRAMITE", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcConfTramite.findAll", query = "SELECT g FROM GcConfTramite g")})
public class GcConfTramite implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "N_CONF_TRA_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeConTramite")
    //@SequenceGenerator(name = "SeqGeConTramite", sequenceName = "SEQ_GC_CONF_TRAMITE")
    private Long nConfTraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ATENCION_PROM", nullable = false)
    private BigInteger nAtencionProm;
    @Column(name = "N_TIEMPO_ESPERA")
    private BigInteger nTiempoEspera;
    @Column(name = "N_TIEMPO_HOLGURA")
    private BigInteger nTiempoHolgura;
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
    @JoinColumn(name = "N_TRAMITE_ID", referencedColumnName = "N_TRAMITE_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcTramite nTramiteId;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Column(name = "N_COMPORTAMIENTO")
    private Long nComportamiento;
    @Column(name = "N_PESO")
    private Long nPeso;
    @Column(name = "N_PROM_ESPERA")
    private Double nPromEspera;
    @Column(name = "N_PROM_ATENCION")
    private Double nPromAtencion;
    
    public GcConfTramite() {
    }

    public GcConfTramite(Long nConfTraId) {
        this.nConfTraId = nConfTraId;
    }

    public GcConfTramite(Long nConfTraId, String cUnidadRecep, BigInteger nAtencionProm, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia,BigInteger nComportamiento,BigInteger nPeso) {
        this.nConfTraId = nConfTraId;
        this.nAtencionProm = nAtencionProm;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
    }

    public Long getNConfTraId() {
        return nConfTraId;
    }

    public void setNConfTraId(Long nConfTraId) {
        this.nConfTraId = nConfTraId;
    }

    public BigInteger getNAtencionProm() {
        return nAtencionProm;
    }

    public void setNAtencionProm(BigInteger nAtencionProm) {
        this.nAtencionProm = nAtencionProm;
    }

    public BigInteger getNTiempoEspera() {
        return nTiempoEspera;
    }

    public void setNTiempoEspera(BigInteger nTiempoEspera) {
        this.nTiempoEspera = nTiempoEspera;
    }

    public BigInteger getNTiempoHolgura() {
        return nTiempoHolgura;
    }

    public void setNTiempoHolgura(BigInteger nTiempoHolgura) {
        this.nTiempoHolgura = nTiempoHolgura;
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

    public GcTramite getNTramiteId() {
        return nTramiteId;
    }

    public void setNTramiteId(GcTramite nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nConfTraId != null ? nConfTraId.hashCode() : 0);
        return hash;
    }
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcConfTramite)) {
            return false;
        }
        GcConfTramite other = (GcConfTramite) object;
        if ((this.nConfTraId == null && other.nConfTraId != null) || (this.nConfTraId != null && !this.nConfTraId.equals(other.nConfTraId))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GcConfTramite [nConfTraId=");
		builder.append(nConfTraId);
		builder.append(", nAtencionProm=");
		builder.append(nAtencionProm);
		builder.append(", nTiempoEspera=");
		builder.append(nTiempoEspera);
		builder.append(", nTiempoHolgura=");
		builder.append(nTiempoHolgura);
		builder.append(", cUsuarioCrea=");
		builder.append(cUsuarioCrea);
		builder.append(", cUsuarioModi=");
		builder.append(cUsuarioModi);
		builder.append(", fiVigencia=");
		builder.append(fiVigencia);
		builder.append(", ffVigencia=");
		builder.append(ffVigencia);
		builder.append(", fModifica=");
		builder.append(fModifica);
		builder.append(", nTramiteId=");
		builder.append(nTramiteId);
		builder.append(", cUnidadRecep=");
		builder.append(cUnidadRecep);
		builder.append(", nComportamiento=");
		builder.append(nComportamiento);
		builder.append(", nPeso=");
		builder.append(nPeso);
		builder.append(", nPromEspera=");
		builder.append(nPromEspera);
		builder.append(", nPromAtencion=");
		builder.append(nPromAtencion);
		builder.append("]");
		return builder.toString();
	}

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public Long getnComportamiento() {
        return nComportamiento;
    }

    public void setnComportamiento(Long nComportamiento) {
        this.nComportamiento = nComportamiento;
    }

    public Long getnPeso() {
        return nPeso;
    }

    public void setnPeso(Long nPeso) {
        this.nPeso = nPeso;
    }

	public Double getnPromEspera() {
		return nPromEspera;
	}

	public void setnPromEspera(Double nPromEspera) {
		this.nPromEspera = nPromEspera;
	}

	public Double getnPromAtencion() {
		return nPromAtencion;
	}

	public void setnPromAtencion(Double nPromAtencion) {
		this.nPromAtencion = nPromAtencion;
	}

	
    
    

    
}
