/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.util.List;
import sv.global.colas.entities.GcTramite;
import sv.global.colas.entities.TbUnidadRecep;

/**
 *
 * @author Owner
 */
public class MonitoreoTramitePojo {
    private List<GcTramite> tramiteList;
    private List<TbUnidadRecep> UnidadesList;
    private Long nTramiteId;
    private String cunidadRecepId;
    private String unidadRecep;
    private String valora;
    
    /*variables para los tramites*/
    private String maxEspera;
    private String maxLlamado;
    private String maxProceso;
    private String minEspera;
    private String minLlamado;
    private String minProceso;
    private String promEspera;
    private String promLlamado;
    private String promProceso;
    private String modaEspera;
    private String modaLlamado;
    private String modaProceso;
    private MonitoreoSeccionTramites mst;

    public MonitoreoSeccionTramites getMst() {
        return mst;
    }

    public void setMst(MonitoreoSeccionTramites mst) {
        this.mst = mst;
    }
    private List<MonitoreoSeccionUsuarios> usuarios;

    public List<GcTramite> getTramiteList() {
        return tramiteList;
    }

    public void setTramiteList(List<GcTramite> tramiteList) {
        this.tramiteList = tramiteList;
    }

    public Long getnTramiteId() {
        return nTramiteId;
    }

    public void setnTramiteId(Long nTramiteId) {
        this.nTramiteId = nTramiteId;
    }

    public List<TbUnidadRecep> getUnidadesList() {
        return UnidadesList;
    }

    public void setUnidadesList(List<TbUnidadRecep> UnidadesList) {
        this.UnidadesList = UnidadesList;
    }

    public String getCunidadRecepId() {
        return cunidadRecepId;
    }

    public void setCunidadRecepId(String cunidadRecepId) {
        this.cunidadRecepId = cunidadRecepId;
    }

    public String getValora() {
        return valora;
    }

    public void setValora(String valora) {
        this.valora = valora;
    }

    public String getUnidadRecep() {
        return unidadRecep;
    }

    public void setUnidadRecep(String unidadRecep) {
        this.unidadRecep = unidadRecep;
    }

    public String getMaxEspera() {
        return maxEspera;
    }

    public void setMaxEspera(String maxEspera) {
        this.maxEspera = maxEspera;
    }

    public String getMaxLlamado() {
        return maxLlamado;
    }

    public void setMaxLlamado(String maxLlamado) {
        this.maxLlamado = maxLlamado;
    }

    public String getMaxProceso() {
        return maxProceso;
    }

    public void setMaxProceso(String maxProceso) {
        this.maxProceso = maxProceso;
    }

    public String getMinEspera() {
        return minEspera;
    }

    public void setMinEspera(String minEspera) {
        this.minEspera = minEspera;
    }

    public String getMinLlamado() {
        return minLlamado;
    }

    public void setMinLlamado(String minLlamado) {
        this.minLlamado = minLlamado;
    }

    public String getMinProceso() {
        return minProceso;
    }

    public void setMinProceso(String minProceso) {
        this.minProceso = minProceso;
    }

    public String getPromEspera() {
        return promEspera;
    }

    public void setPromEspera(String promEspera) {
        this.promEspera = promEspera;
    }

    public String getPromLlamado() {
        return promLlamado;
    }

    public void setPromLlamado(String promLlamado) {
        this.promLlamado = promLlamado;
    }

    public String getPromProceso() {
        return promProceso;
    }

    public void setPromProceso(String promProceso) {
        this.promProceso = promProceso;
    }

    public String getModaEspera() {
        return modaEspera;
    }

    public void setModaEspera(String modaEspera) {
        this.modaEspera = modaEspera;
    }

    public String getModaLlamado() {
        return modaLlamado;
    }

    public void setModaLlamado(String modaLlamado) {
        this.modaLlamado = modaLlamado;
    }

    public String getModaProceso() {
        return modaProceso;
    }

    public void setModaProceso(String modaProceso) {
        this.modaProceso = modaProceso;
    }

    public List<MonitoreoSeccionUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<MonitoreoSeccionUsuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
