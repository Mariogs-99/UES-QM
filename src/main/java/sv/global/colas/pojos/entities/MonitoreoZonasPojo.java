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
public class MonitoreoZonasPojo {
    
    private List<MonitoreoUsuarioPojo> monitoreoUsuarioPojo;
    private List<MonitoreoColasPojo> monitoreoColasPojo;
    private String nombre;
    private Long zonaId;
    private Long saturacion;

    public List<MonitoreoUsuarioPojo> getMonitoreoUsuarioPojo() {
        return monitoreoUsuarioPojo;
    }

    public void setMonitoreoUsuarioPojo(List<MonitoreoUsuarioPojo> monitoreoUsuarioPojo) {
        this.monitoreoUsuarioPojo = monitoreoUsuarioPojo;
    }

    public List<MonitoreoColasPojo> getMonitoreoColasPojo() {
        return monitoreoColasPojo;
    }

    public void setMonitoreoColasPojo(List<MonitoreoColasPojo> monitoreoColasPojo) {
        this.monitoreoColasPojo = monitoreoColasPojo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getZonaId() {
        return zonaId;
    }

    public void setZonaId(Long zonaId) {
        this.zonaId = zonaId;
    }

    public Long getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(Long saturacion) {
        this.saturacion = saturacion;
    }
    
}
