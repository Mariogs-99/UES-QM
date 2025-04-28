/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "GC_MULTIMEDIA", catalog = "", schema = "")
@NamedQueries({
    @NamedQuery(name = "GcMultimedia.findAll", query = "SELECT g FROM GcMultimedia g")})
public class GcMultimedia implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_MULTIMEDIA_ID",  precision = 22, scale = 0)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SeqGeMultimedia")
    //@SequenceGenerator(name = "SeqGeMultimedia", sequenceName = "SEQ_GC_MULTIMEDIA")
    private Long nMultimediaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "S_TIPO", nullable = false, length = 256)
    private String sTipo;
    @Lob    
    @Column(name = "X_ARCHIVO")
    private Blob xArchivo;
    //private Serializable xArchivo;
    //@NotNull
    @Column(name = "N_DURACION", nullable = true)
    private Long nDuracion;
    @Column(name = "FI_MULTIMEDIA", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiMultimedia;
    @Column(name = "FF_MULTIMEDIA", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ffMultimedia;
    @Size(max = 256)
    @Column(name = "D_MULTIMEDIA", length = 256)
    private String dMultimedia;
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

    @Column(name = "FI_VIGENCIA", nullable = true)
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
    @Size(max = 1024)
    @Column(name = "S_RUTA", length = 1024)
    private String sRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "S_MULTIMEDIA", nullable = false, length = 256)
    private String sMultimedia;

    public String getsMultimedia() {
        return sMultimedia;
    }

    public void setsMultimedia(String sMultimedia) {
        this.sMultimedia = sMultimedia;
    }
    

    public GcMultimedia() {
    }

    public GcMultimedia(Long nMultimediaId) {
        this.nMultimediaId = nMultimediaId;
    }

    public GcMultimedia(Long nMultimediaId, String sTipo, Long nDuracion, Date fiMultimedia, Date ffMultimedia, String cUsuarioCrea, String cUsuarioModi, Date fiVigencia, short bActiva) {
        this.nMultimediaId = nMultimediaId;
        this.sTipo = sTipo;
        this.nDuracion = nDuracion;
        this.fiMultimedia = fiMultimedia;
        this.ffMultimedia = ffMultimedia;
        this.cUsuarioCrea = cUsuarioCrea;
        this.cUsuarioModi = cUsuarioModi;
        this.fiVigencia = fiVigencia;
        this.bActiva = bActiva;
    }

    public Long getNMultimediaId() {
        return nMultimediaId;
    }

    public void setNMultimediaId(Long nMultimediaId) {
        this.nMultimediaId = nMultimediaId;
    }

    public String getSTipo() {
        return sTipo;
    }

    public void setSTipo(String sTipo) {
        this.sTipo = sTipo;
    }

    public Blob getXArchivo() {
        return xArchivo;
    }

    public void setXArchivo(Blob xArchivo) {
        this.xArchivo = xArchivo;
    }

    public Long getNDuracion() {
        return nDuracion;
    }

    public void setNDuracion(Long nDuracion) {
        this.nDuracion = nDuracion;
    }

    public Date getFiMultimedia() {
        return fiMultimedia;
    }

    public void setFiMultimedia(Date fiMultimedia) {
        this.fiMultimedia = fiMultimedia;
    }

    public Date getFfMultimedia() {
        return ffMultimedia;
    }

    public void setFfMultimedia(Date ffMultimedia) {
        this.ffMultimedia = ffMultimedia;
    }

    public String getDMultimedia() {
        return dMultimedia;
    }

    public void setDMultimedia(String dMultimedia) {
        this.dMultimedia = dMultimedia;
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

    public String getSRuta() {
        return sRuta;
    }

    public void setSRuta(String sRuta) {
        this.sRuta = sRuta;
    }

//    public List<GcConfRepro> getGcConfReproList() {
//        return gcConfReproList;
//    }
//
//    public void setGcConfReproList(List<GcConfRepro> gcConfReproList) {
//        this.gcConfReproList = gcConfReproList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nMultimediaId != null ? nMultimediaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcMultimedia)) {
            return false;
        }
        GcMultimedia other = (GcMultimedia) object;
        if ((this.nMultimediaId == null && other.nMultimediaId != null) || (this.nMultimediaId != null && !this.nMultimediaId.equals(other.nMultimediaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.colas.entities.GcMultimedia[ nMultimediaId=" + nMultimediaId + " ]";
    }
    
}
