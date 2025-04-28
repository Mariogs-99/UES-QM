/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ever.argueta
 */
public class MensajePojo implements Serializable {
    
    private Long id;
    private String usuario;
    private String mensaje;
    private String tipo;
    private Boolean correo;
    private Boolean pantalla;
    private Date creacion;
    
    public MensajePojo() {}
    
    public MensajePojo(Long id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getCorreo() {
        return correo;
    }

    public void setCorreo(Boolean correo) {
        this.correo = correo;
    }

    public Boolean getPantalla() {
        return pantalla;
    }

    public void setPantalla(Boolean pantalla) {
        this.pantalla = pantalla;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }
}
