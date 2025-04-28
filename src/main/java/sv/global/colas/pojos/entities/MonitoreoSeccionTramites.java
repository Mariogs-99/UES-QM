/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.math.BigDecimal;

/**
 *
 * @author Owner
 */
public class MonitoreoSeccionTramites {
    private String tramite;
    private Long tramiteId;
    private Long esperando;
    private Long hora;
    private Long min45;
    private Long min30;
    private Long min15;
    private Long actual;
    private Long procesados;

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public Long getTramiteId() {
        return tramiteId;
    }

    public void setTramiteId(Long tramiteId) {
        this.tramiteId = tramiteId;
    }

    public Long getEsperando() {
        return esperando;
    }

    public void setEsperando(Long esperando) {
        this.esperando = esperando;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }

    public Long getMin45() {
        return min45;
    }

    public void setMin45(Long min45) {
        this.min45 = min45;
    }

    public Long getMin30() {
        return min30;
    }

    public void setMin30(Long min30) {
        this.min30 = min30;
    }

    public Long getMin15() {
        return min15;
    }

    public void setMin15(Long min15) {
        this.min15 = min15;
    }

    public Long getActual() {
        return actual;
    }

    public void setActual(Long actual) {
        this.actual = actual;
    }

    public Long getProcesados() {
        return procesados;
    }

    public void setProcesados(Long procesados) {
        this.procesados = procesados;
    }
    
}
