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
@Table(name = "GC_ZONA", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcZona.findAll", query = "SELECT g FROM GcZona g where g.bActiva in(0.1)")})
public class GcZona implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "N_ZONA_ID",unique=true, nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeZona")
    //@SequenceGenerator(name = "SeqGeZona", sequenceName = "SEQ_GC_ZONA
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long nZonaId;
    
    @Size(max = 256)
    @Column(name = "S_NOMBRE", length = 256)
    private String sNombre;
    
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    
    @Column(name = "N_SILLAS_ESPERA")
    private BigInteger nSillasEspera;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private int bActiva;
    
    @Size(max = 256)
    @Column(name = "D_ZONA", length = 256)
    private String dZona;
    
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
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nZonaId")
    private List<GcEscritorio> gcEscritorioList;


    public GcZona() {
    }


    public GcZona(Long nZonaId) {
        this.nZonaId = nZonaId;
    }


    public GcZona(Long nZonaId, int bActiva, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, String sNombre) {
        this.nZonaId = nZonaId;
        this.bActiva = bActiva;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.sNombre = sNombre;
    }


    public Long getNZonaId() {
        return nZonaId;
    }


    public void setNZonaId(Long nZonaId) {
        this.nZonaId = nZonaId;
    }


    public String getSNombre() {
        return sNombre;
    }


    public void setSNombre(String sNombre) {
        this.sNombre = sNombre;
    }
    
    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }


    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }


    public BigInteger getNSillasEspera() {
        return nSillasEspera;
    }


    public void setNSillasEspera(BigInteger nSillasEspera) {
        this.nSillasEspera = nSillasEspera;
    }


    public int getBActiva() {
        return bActiva;
    }


    public void setBActiva(int bActiva) {
        this.bActiva = bActiva;
    }


    public String getDZona() {
        return dZona;
    }


    public void setDZona(String dZona) {
        this.dZona = dZona;
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


    public List<GcEscritorio> getGcEscritorioList() {
        return gcEscritorioList;
    }


    public void setGcEscritorioList(List<GcEscritorio> gcEscritorioList) {
        this.gcEscritorioList = gcEscritorioList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nZonaId != null ? nZonaId.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcZona)) {
            return false;
        }
        GcZona other = (GcZona) object;
        if ((this.nZonaId == null && other.nZonaId != null) || (this.nZonaId != null && !this.nZonaId.equals(other.nZonaId))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcZona[ nZonaId=" + nZonaId + " ]";
    }
    
}
