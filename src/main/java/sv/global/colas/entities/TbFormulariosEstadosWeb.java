/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author AngelMontenegro
 */
@Entity
@Table(name = "TB_FORMULARIOS_ESTADOS_WEB", schema = "CATALOGOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbFormulariosEstadosWeb.findAll", query = "SELECT t FROM TbFormulariosEstadosWeb t")})
public class TbFormulariosEstadosWeb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "C_ESTADO_WEB")
    private Integer cEstadoWeb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "D_ESTADO_WEB")
    private String dEstadoWeb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "C_USUARIO")
    private String cUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "F_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fIngreso;
    @OneToMany(mappedBy = "cEstadoWeb")
    private Set<TbFormulariosVersiones> tbFormulariosVersionesSet;

    public TbFormulariosEstadosWeb() {
    }

    public TbFormulariosEstadosWeb(Integer cEstadoWeb) {
        this.cEstadoWeb = cEstadoWeb;
    }

    public TbFormulariosEstadosWeb(Integer cEstadoWeb, String dEstadoWeb, String cUsuario, Date fIngreso) {
        this.cEstadoWeb = cEstadoWeb;
        this.dEstadoWeb = dEstadoWeb;
        this.cUsuario = cUsuario;
        this.fIngreso = fIngreso;
    }

    public Integer getCEstadoWeb() {
        return cEstadoWeb;
    }

    public void setCEstadoWeb(Integer cEstadoWeb) {
        this.cEstadoWeb = cEstadoWeb;
    }

    public String getDEstadoWeb() {
        return dEstadoWeb;
    }

    public void setDEstadoWeb(String dEstadoWeb) {
        this.dEstadoWeb = dEstadoWeb;
    }

    public String getCUsuario() {
        return cUsuario;
    }

    public void setCUsuario(String cUsuario) {
        this.cUsuario = cUsuario;
    }

    public Date getFIngreso() {
        return fIngreso;
    }

    public void setFIngreso(Date fIngreso) {
        this.fIngreso = fIngreso;
    }

    public Set<TbFormulariosVersiones> getTbFormulariosVersionesSet() {
        return tbFormulariosVersionesSet;
    }

    public void setTbFormulariosVersionesSet(Set<TbFormulariosVersiones> tbFormulariosVersionesSet) {
        this.tbFormulariosVersionesSet = tbFormulariosVersionesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cEstadoWeb != null ? cEstadoWeb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbFormulariosEstadosWeb)) {
            return false;
        }
        TbFormulariosEstadosWeb other = (TbFormulariosEstadosWeb) object;
        if ((this.cEstadoWeb == null && other.cEstadoWeb != null) || (this.cEstadoWeb != null && !this.cEstadoWeb.equals(other.cEstadoWeb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mh.dgii.model.TbFormulariosEstadosWeb[ cEstadoWeb=" + cEstadoWeb + " ]";
    }
    
}
