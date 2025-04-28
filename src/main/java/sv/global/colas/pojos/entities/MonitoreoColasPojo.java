/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

/**
 *
 * @author Owner
 */
public class MonitoreoColasPojo {
    
    private String tramite;
    private Long totEspera;
    private Long totProcesados;
    private Long enProceso;
    private Long totAnulados;
    private Long saturacion;
    private String tiquetes;

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public Long getTotEspera() {
        return totEspera;
    }

    public void setTotEspera(Long totEspera) {
        this.totEspera = totEspera;
    }

    public Long getTotProcesados() {
        return totProcesados;
    }

    public void setTotProcesados(Long totProcesados) {
        this.totProcesados = totProcesados;
    }

    public Long getEnProceso() {
        return enProceso;
    }

    public void setEnProceso(Long enProceso) {
        this.enProceso = enProceso;
    }

    public Long getTotAnulados() {
        return totAnulados;
    }

    public void setTotAnulados(Long totAnulados) {
        this.totAnulados = totAnulados;
    }

    public Long getSaturacion() {
        return saturacion;
    }

    public void setSaturacion(Long saturacion) {
        this.saturacion = saturacion;
    }

    public String getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(String tiquetes) {
        this.tiquetes = tiquetes;
    }
    
}
