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
public class MonitoreoSeccionUsuarios {
    private String usuario;
    private String estado;
    private Long escritorioId;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getEscritorioId() {
        return escritorioId;
    }

    public void setEscritorioId(Long escritorioId) {
        this.escritorioId = escritorioId;
    }
    
    
}
