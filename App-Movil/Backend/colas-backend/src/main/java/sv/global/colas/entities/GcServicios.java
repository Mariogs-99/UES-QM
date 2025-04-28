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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



/**
 *
 * @author Alcides Nolasco
 */
@Entity
@Table(name = "GC_SERVICIOS", catalog = "", schema = "")

public class GcServicios extends PanacheEntityBase  {
    private static final long serialVersionUID = 1L;
    @Id
   
    @Column(name = "N_SERVICIOS_ID", nullable = false, precision = 22, scale = 0)  
    //@GeneratedValue

    private Long nServiciosId;
  
    @Column(name = "D_SERVICIOS", length = 256)
    private String dServicios;

    @Column(name = "S_NOMBRE", length = 256)
    private String sNombre;
   
    @Column(name = "C_USUARIO_CREA", nullable = false, length = 100)
    private String cUsuarioCrea;

    @Column(name = "C_USUARIO_MODI", nullable = false, length = 100)
    private String cUsuarioModi;
 
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

    @Column(name = "B_ACTIVA", nullable = false)
    private short bActiva;

   /*
    @JsonIgnoreProperties("nServiciosId")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nServiciosId")
    private List<GcTramite> gcTramiteList;
    */
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

   /* 
    public List<GcTramite> getGcTramiteList() {
        return gcTramiteList;
    }


    public void setGcTramiteList(List<GcTramite> gcTramiteList) {
        this.gcTramiteList = gcTramiteList;
    }

  */
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

