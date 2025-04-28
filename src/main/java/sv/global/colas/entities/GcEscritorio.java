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
@Table(name = "GC_ESCRITORIO", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcEscritorio.findAll", query = "SELECT g FROM GcEscritorio g where g.bActiva in(0,1)")})
public class GcEscritorio implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ESCRITORIO_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeEscritorio")
    //@SequenceGenerator(name = "SeqGeEscritorio", sequenceName = "SEQ_GC_ESCRITORIO")
    private Long nEscritorioId;
    
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private int bActiva;
    @Size(max = 256)
    @Column(name = "D_ESCRITORIO", length = 256)
    private String dEscritorio;
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
    @Size(max = 1)
    @Column(name = "C_IDENTIFICADOR", length = 1)
    private String cIdentificador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_NUM_ESCRITORIO", nullable = false)
    private BigInteger nNumEscritorio;
    @JoinColumn(name = "N_ZONA_ID", referencedColumnName = "N_ZONA_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcZona nZonaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nEscritorioId")
    private List<GcUsuario> gcUsuarioList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_FILA_ESP", nullable = false)
    private int bFilaEsp;
    
    public GcEscritorio() {
    }

    public GcEscritorio(Long nEscritorioId) {
        this.nEscritorioId = nEscritorioId;
    }

    public GcEscritorio(Long nEscritorioId, TbUnidadRecep cUnidadRecep, int bActiva, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, Date ffVigencia, BigInteger nNumEscritorio, int bFilaEsp) {
        this.nEscritorioId = nEscritorioId;
        this.cUnidadRecep = cUnidadRecep;
        this.bActiva = bActiva;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.ffVigencia = ffVigencia;
        this.nNumEscritorio = nNumEscritorio;
        this.bFilaEsp=bFilaEsp;
    }

    public Long getNEscritorioId() {
        return nEscritorioId;
    }

    public void setNEscritorioId(Long nEscritorioId) {
        this.nEscritorioId = nEscritorioId;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public int getBActiva() {
        return bActiva;
    }

    public void setBActiva(int bActiva) {
        this.bActiva = bActiva;
    }

    public String getDEscritorio() {
        return dEscritorio;
    }

    public void setDEscritorio(String dEscritorio) {
        this.dEscritorio = dEscritorio;
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

    public String getCIdentificador() {
        return cIdentificador;
    }

    public void setCIdentificador(String cIdentificador) {
        this.cIdentificador = cIdentificador;
    }

    public BigInteger getNNumEscritorio() {
        return nNumEscritorio;
    }

    public void setNNumEscritorio(BigInteger nNumEscritorio) {
        this.nNumEscritorio = nNumEscritorio;
    }

    public GcZona getNZonaId() {
        return nZonaId;
    }

    public void setNZonaId(GcZona nZonaId) {
        this.nZonaId = nZonaId;
    }

    public List<GcUsuario> getGcUsuarioList() {
        return gcUsuarioList;
    }

    public void setGcUsuarioList(List<GcUsuario> gcUsuarioList) {
        this.gcUsuarioList = gcUsuarioList;
    }
    
    public int getBFilaEsp() {
        return bFilaEsp;
    }

    public void setBFilaEsp(int bFilaEsp) {
        this.bFilaEsp = bFilaEsp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nEscritorioId != null ? nEscritorioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcEscritorio)) {
            return false;
        }
        GcEscritorio other = (GcEscritorio) object;
        if ((this.nEscritorioId == null && other.nEscritorioId != null) || (this.nEscritorioId != null && !this.nEscritorioId.equals(other.nEscritorioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcEscritorio[ nEscritorioId=" + nEscritorioId + " ]";
    }
    
}
