/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "GC_TRAMITE", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcTramite.findAll", query = "SELECT g FROM GcTramite g")})
public class GcTramite implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "N_TRAMITE_ID", nullable = false, precision = 22)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeTramite")
    //@SequenceGenerator(name = "SeqGeTramite", sequenceName = "SEQ_GC_TRAMITE")
    private Long nTramiteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_PESO", nullable = false)
    private BigInteger nPeso;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "N_ICONO")//, nullable = false)
    private int nIcono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_NIT_REQUERIDO", nullable = false)
    private short bNitRequerido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ESCALAMIENTO", nullable = false)
    private short bEscalamiento;
    @Size(max = 256)
    @Column(name = "D_TRAMITE", length = 256)
    private String dTramite;
    @Column(name = "B_RESERVA_CITA", nullable = false)
    private Integer bReservaCita;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "S_NOMBRE", nullable = false, length = 256)
    private String sNombre;
    @Column(name = "N_ORDEN")
    private Long nOrden;
    @JoinTable(name = "GC_USR_TRA", joinColumns = {
        @JoinColumn(name = "N_TRAMITE_ID", referencedColumnName = "N_TRAMITE_ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "C_USUARIO", referencedColumnName = "C_USUARIO", nullable = false)})
    @ManyToMany
    private List<GcUsuario> gcUsuarioList;
    @JoinColumn(name = "N_SERVICIOS_ID", referencedColumnName = "N_SERVICIOS_ID", nullable = false)
    @ManyToOne(optional = false)
    private GcServicios nServiciosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nTramiteId")
    private List<GcReservaCita> gcReservaCitaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nTramiteId")
    private List<GcConfTramite> gcConfTramiteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nTramiteId")
    private List<GcTiquete> gcTiqueteList;
    
    public GcTramite() {
    }

    public GcTramite(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public GcTramite(Long nTramiteId, BigInteger nPeso ,short bNitRequerido, short bEscalamiento, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, short bActiva, String sNombre) {
        this.nTramiteId = nTramiteId;
        this.nPeso = nPeso;
        this.bNitRequerido = bNitRequerido;
        this.bEscalamiento = bEscalamiento;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.bActiva = bActiva;
        this.sNombre = sNombre;
    }

    public Long getNTramiteId() {
        return nTramiteId;
    }

    public void setNTramiteId(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public BigInteger getNPeso() {
        return nPeso;
    }

    public void setNPeso(BigInteger nPeso) {
        this.nPeso = nPeso;
    }
     public int getNIcono() {
        return nIcono;
    }

    public void setNIcono(int nIco) {
        this.nIcono = nIco;
    }
    public short getBNitRequerido() {
        return bNitRequerido;
    }

    public void setBNitRequerido(short bNitRequerido) {
        this.bNitRequerido = bNitRequerido;
    }

    public short getBEscalamiento() {
        return bEscalamiento;
    }

    public void setBEscalamiento(short bEscalamiento) {
        this.bEscalamiento = bEscalamiento;
    }

    public String getDTramite() {
        return dTramite;
    }

    public void setDTramite(String dTramite) {
        this.dTramite = dTramite;
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

    public short getBActiva() {
        return bActiva;
    }

    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getSNombre() {
        return sNombre;
    }

    public void setSNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public Long getNOrden() {
        return nOrden;
    }

    public void setNOrden(Long nOrden) {
        this.nOrden = nOrden;
    }

    public List<GcUsuario> getGcUsuarioList() {
        return gcUsuarioList;
    }

    public void setGcUsuarioList(List<GcUsuario> gcUsuarioList) {
        this.gcUsuarioList = gcUsuarioList;
    }

    public GcServicios getNServiciosId() {
        return nServiciosId;
    }

    public void setNServiciosId(GcServicios nServiciosId) {
        this.nServiciosId = nServiciosId;
    }

    public List<GcReservaCita> getGcReservaCitaList() {
        return gcReservaCitaList;
    }

    public void setGcReservaCitaList(List<GcReservaCita> gcReservaCitaList) {
        this.gcReservaCitaList = gcReservaCitaList;
    }

    public List<GcConfTramite> getGcConfTramiteList() {
        return gcConfTramiteList;
    }

    public void setGcConfTramiteList(List<GcConfTramite> gcConfTramiteList) {
        this.gcConfTramiteList = gcConfTramiteList;
    }

    public List<GcTiquete> getGcTiqueteList() {
        return gcTiqueteList;
    }

    public void setGcTiqueteList(List<GcTiquete> gcTiqueteList) {
        this.gcTiqueteList = gcTiqueteList;
    }
    
    public Integer getbReservaCita() {
        return bReservaCita;
    }

    public void setbReservaCita(Integer bReservaCita) {
        this.bReservaCita = bReservaCita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nTramiteId != null ? nTramiteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcTramite)) {
            return false;
        }
        GcTramite other = (GcTramite) object;
        if ((this.nTramiteId == null && other.nTramiteId != null) || (this.nTramiteId != null && !this.nTramiteId.equals(other.nTramiteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcTramite[ nTramiteId=" + nTramiteId + " ]";
    }
    
}
