/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.util.List;

/**
 *
 * @author Owner
 */
public class MonitoreoCsPojo {

    private List<MonitoreoZonasPojo> monitoreoZonasPojo;
    private String nombre;
    private String csId;
    private Long saturacion;
    private Integer promedioEspera;
    private Integer totalTiquetes; // Nuevo campo agregado
    private List<Object[]> topTramites;  //nuevo campo
    private List<String> ultimosTramites; //nuevo campo 

    public List<MonitoreoZonasPojo> getMonitoreoZonasPojo() {
        return monitoreoZonasPojo;
    }

    public void setMonitoreoZonasPojo(List<MonitoreoZonasPojo> monitoreoZonasPojo) {
        this.monitoreoZonasPojo = monitoreoZonasPojo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public Long getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(Long saturacion) {
        this.saturacion = saturacion;
    }

    public Integer getPromedioEspera() {
        return promedioEspera;
    }

    public void setPromedioEspera(Integer promedioEspera) {
        this.promedioEspera = promedioEspera;
    }

    // Getter y Setter para totalTiquetes
    public Integer getTotalTiquetes() {
        return totalTiquetes;
    }

    public void setTotalTiquetes(Integer totalTiquetes) {
        this.totalTiquetes = totalTiquetes;
    }
    
    // Getter y Setter para topTramites
    public List<Object[]> getTopTramites() {
        return topTramites;
    }

    public void setTopTramites(List<Object[]> topTramites) {
        this.topTramites = topTramites;
    }
    
    // Getter y Setter para ultimosTramites
    public List<String> getUtimosTramites() {
        return ultimosTramites;
    }

    public void setUltimosTramites(List<String> ultimosTramites) {
        this.ultimosTramites = ultimosTramites;
    }
}
