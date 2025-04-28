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
public class MonitoreoSeccionPojo {
    private Long seccionId;
    private String scNombre;
    private String maximo;
    private String minimo;
    private String promedio;
    private String moda;
    private Long esperando;
    private Long Procesados;
    private List<MonitoreoSeccionUsuarios> usuarios;
    private List<MonitoreoSeccionTramites> tramites;

    public String getMaximo() {
        return maximo;
    }

    public void setMaximo(String maximo) {
        this.maximo = maximo;
    }

    public String getMinimo() {
        return minimo;
    }

    public void setMinimo(String minimo) {
        this.minimo = minimo;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getModa() {
        return moda;
    }

    public void setModa(String moda) {
        this.moda = moda;
    }

    public Long getEsperando() {
        return esperando;
    }

    public void setEsperando(Long esperando) {
        this.esperando = esperando;
    }

    public Long getProcesados() {
        return Procesados;
    }

    public void setProcesados(Long Procesados) {
        this.Procesados = Procesados;
    }

    public List<MonitoreoSeccionUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<MonitoreoSeccionUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<MonitoreoSeccionTramites> getTramites() {
        return tramites;
    }

    public void setTramites(List<MonitoreoSeccionTramites> tramites) {
        this.tramites = tramites;
    }

    public Long getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(Long seccionId) {
        this.seccionId = seccionId;
    }

    public String getScNombre() {
        return scNombre;
    }

    public void setScNombre(String scNombre) {
        this.scNombre = scNombre;
    }
    
    
    
}
