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
@Table(name = "GC_SERVICIOS", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcServicios.findAll", query = "SELECT g FROM GcServicios g")}
        )
public class GcServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "N_SERVICIOS_ID", nullable = false, precision = 22, scale = 0)  
    //@GeneratedValue
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeServicio")
    //@SequenceGenerator(name = "SeqGeServicio", sequenceName = "SEQ_GC_SERVICIOS")
    private Long nServiciosId;
    @Size(max = 256)
    @Column(name = "D_SERVICIOS", length = 256)
    private String dServicios;
    @Size(max = 256)
    @Column(name = "S_NOMBRE", length = 256)
    private String sNombre;
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
    @Column(name = "N_ORDEN")
    private BigInteger nOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nServiciosId")
    private List<GcTramite> gcTramiteList;


    public GcServicios() {
    }


    public GcServicios(Long nServiciosId) {
        this.nServiciosId = nServiciosId;
    }


    public GcServicios(Long nServiciosId, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, short bActiva, String sNombre) {
        this.nServiciosId = nServiciosId;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.bActiva = bActiva;
        this.sNombre = sNombre;
    }


    public Long getNServiciosId() {
        return nServiciosId;
    }


    public void setNServiciosId(Long nServiciosId) {
        this.nServiciosId = nServiciosId;
    }


    public String getDServicios() {
        return dServicios;
    }


    public void setDServicios(String dServicios) {
        this.dServicios = dServicios;
    }
    
    public String getSNombre(){
        return sNombre;
    }
    
    public void setSNombre(String sNombre){
        this.sNombre=sNombre;
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


    public BigInteger getNOrden() {
        return nOrden;
    }


    public void setNOrden(BigInteger nOrden) {
        this.nOrden = nOrden;
    }


    public short getBActiva() {
        return bActiva;
    }


    public void setBActiva(short bActiva) {
        this.bActiva = bActiva;
    }


    public List<GcTramite> getGcTramiteList() {
        return gcTramiteList;
    }


    public void setGcTramiteList(List<GcTramite> gcTramiteList) {
        this.gcTramiteList = gcTramiteList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nServiciosId != null ? nServiciosId.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcServicios)) {
            return false;
        }
        GcServicios other = (GcServicios) object;
        if ((this.nServiciosId == null && other.nServiciosId != null) || (this.nServiciosId != null && !this.nServiciosId.equals(other.nServiciosId))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcServicios[ nServiciosId=" + nServiciosId + " ]";
    }
    
}

