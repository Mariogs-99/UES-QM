/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.util.AutoPopulatingList;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;

/**
 *
 * @autor Ever Argueta
 */
public class ZonaPojo  implements Serializable {
    
    private Long nZonaId;
    private String cunidadRecep;
    private String dunidadRecep;
    private String snombre;
    private BigInteger nSillasEspera;
    private int bActiva;
    private String dZona;
    private List<TbUnidadRecep> unidadesList =  new AutoPopulatingList<TbUnidadRecep>(TbUnidadRecep.class);
    private List<GcZona> zonaList = new AutoPopulatingList<GcZona>(GcZona.class);
    private String cUsuarioCrea;
    private String cUsuarioModi;
    private Date fiVigencia;
    private TbUnidadRecep cUnidadRecep;
    
    public TbUnidadRecep getcUnidadRecep() {
		return cUnidadRecep;
	}

	public void setcUnidadRecep(TbUnidadRecep cUnidadRecep) {
		this.cUnidadRecep = cUnidadRecep;
	}

	public void setZona(GcZona zona){
        this.nZonaId=zona.getNZonaId();
        this.cunidadRecep=zona.getCUnidadRecep().getCunidadRecep();
        this.snombre=zona.getSNombre();
        this.nSillasEspera=zona.getNSillasEspera();
        this.bActiva=zona.getBActiva();
        this.dZona=zona.getDZona();
    }

    public BigInteger getnSillasEspera() {
        return nSillasEspera;
    }

    public void setnSillasEspera(BigInteger nSillasEspera) {
        this.nSillasEspera = nSillasEspera;
    }

    public int getbActiva() {
        return bActiva;
    }

    public void setbActiva(int bActiva) {
        this.bActiva = bActiva;
    }

    public String getdZona() {
        return dZona;
    }

    public void setdZona(String dZona) {
        this.dZona = dZona;
    }

    /**
     * @return the unidadesList
     */
    public List<TbUnidadRecep> getUnidadesList() {
        return unidadesList;
    }

    /**
     * @param unidadesList the unidadesList to set
     */
    public void setUnidadesList(List<TbUnidadRecep> unidadesList) {
        this.unidadesList = unidadesList;
    }


    public String getCunidadRecep() {
        return cunidadRecep;
    }

    public void setCunidadRecep(String cunidadRecep) {
        this.cunidadRecep = cunidadRecep;
    }

    /**
     * @return the snombre
     */
    public String getSnombre() {
        return snombre;
    }

    /**
     * @param snombre the snombre to set
     */
    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    /**
     * @return the zonaList
     */
    public List<GcZona> getZonaList() {
        return zonaList;
    }

    /**
     * @param zonaList the zonaList to set
     */
    public void setZonaList(List<GcZona> zonaList) {
        this.zonaList = zonaList;
    }

    public String getcUsuarioCrea() {
        return cUsuarioCrea;
    }

    public void setcUsuarioCrea(String cUsuarioCrea) {
        this.cUsuarioCrea = cUsuarioCrea;
    }

    public String getcUsuarioModi() {
        return cUsuarioModi;
    }

    public void setcUsuarioModi(String cUsuarioModi) {
        this.cUsuarioModi = cUsuarioModi;
    }

    public Date getFiVigencia() {
        return fiVigencia;
    }

    public void setFiVigencia(Date fiVigencia) {
        this.fiVigencia = fiVigencia;
    }

    public Long getnZonaId() {
        return nZonaId;
    }

    public void setnZonaId(Long nZonaId) {
        this.nZonaId = nZonaId;
    }

    public String getDunidadRecep() {
        return dunidadRecep;
    }

    public void setDunidadRecep(String dunidadRecep) {
        this.dunidadRecep = dunidadRecep;
    }
    


}
