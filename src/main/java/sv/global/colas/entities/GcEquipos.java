/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "GC_EQUIPOS", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcEquipos.findAll", query = "SELECT g FROM GcEquipos g")})
public class GcEquipos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_EQUIPO_ID", nullable = false, precision = 22, scale = 0)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeEquipo")
    //@SequenceGenerator(name = "SeqGeEquipo", sequenceName = "SEQ_GC_EQUIPOS")
    private Long nEquipoId;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "C_UNIDAD_RECEP", referencedColumnName = "C_UNIDAD_RECEP", nullable = false)
    @ManyToOne(optional = false)
    private TbUnidadRecep cUnidadRecep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "S_MARCA", nullable = false, length = 100)
    private String sMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "S_MODELO", nullable = false, length = 50)
    private String sModelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "S_SERIE", nullable = false, length = 50)
    private String sSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "S_NUMERO_INVENTARIO", nullable = false, length = 50)
    private String sNumeroInventario;
    @Size(max = 256)
    @Column(name = "D_EQUIPO", length = 256)
    private String dEquipo;
    @Column(name = "FF_VIGENCIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FI_VIGENCIA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiVigencia;
    @Column(name = "F_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fModifica;
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
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @JoinColumn(name = "N_TIPO_ID", referencedColumnName = "N_TIPO_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcTipoEquipo nTipoId;

    public GcEquipos() {
    }

    public GcEquipos(Long nEquipoId) {
        this.nEquipoId = nEquipoId;
    }

    public GcEquipos(Long nEquipoId, String sMarca, String sModelo, String sSerie, String sNumeroInventario, Date fiVigencia, String cUsuarioCrea, String cUsuarioModi, short bActiva) {
        this.nEquipoId = nEquipoId;
        this.sMarca = sMarca;
        this.sModelo = sModelo;
        this.sSerie = sSerie;
        this.sNumeroInventario = sNumeroInventario;
        this.fiVigencia = fiVigencia;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.bActiva = bActiva;
    }

    public Long getNEquipoId() {
        return nEquipoId;
    }

    public void setNEquipoId(Long nEquipoId) {
        this.nEquipoId = nEquipoId;
    }

    public TbUnidadRecep getCUnidadRecep() {
        return cUnidadRecep;
    }

    public void setCUnidadRecep(TbUnidadRecep cUnidadRecep) {
        this.cUnidadRecep = cUnidadRecep;
    }

    public String getSMarca() {
        return sMarca;
    }

    public void setSMarca(String sMarca) {
        this.sMarca = sMarca;
    }

    public String getSModelo() {
        return sModelo;
    }

    public void setSModelo(String sModelo) {
        this.sModelo = sModelo;
    }

    public String getSSerie() {
        return sSerie;
    }

    public void setSSerie(String sSerie) {
        this.sSerie = sSerie;
    }

    public String getSNumeroInventario() {
        return sNumeroInventario;
    }

    public void setSNumeroInventario(String sNumeroInventario) {
        this.sNumeroInventario = sNumeroInventario;
    }

    public String getDEquipo() {
        return dEquipo;
    }

    public void setDEquipo(String dEquipo) {
        this.dEquipo = dEquipo;
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

    public short getBActiva() {
        return bActiva;
    }

    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public GcTipoEquipo getNTipoId() {
        return nTipoId;
    }

    public void setNTipoId(GcTipoEquipo nTipoId) {
        this.nTipoId = nTipoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nEquipoId != null ? nEquipoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcEquipos)) {
            return false;
        }
        GcEquipos other = (GcEquipos) object;
        if ((this.nEquipoId == null && other.nEquipoId != null) || (this.nEquipoId != null && !this.nEquipoId.equals(other.nEquipoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcEquipos[ nEquipoId=" + nEquipoId + " ]";
    }
    
}
