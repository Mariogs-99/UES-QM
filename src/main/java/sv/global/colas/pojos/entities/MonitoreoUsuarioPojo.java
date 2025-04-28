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
public class MonitoreoUsuarioPojo {
    
    
    private String nombre;
    private Long escritorioid;
    private String escritorio;
    private String rol;
    private String estado;
    private String tramites;
    private String secciones;
    private String toatendidos;
    private String isesion;
    private String iproceso;

    public MonitoreoUsuarioPojo(){
    }
    
    public MonitoreoUsuarioPojo(String nombre,Long escritorioid,String escritorio,String rol,String estado,String tramites,String secciones){
        this.nombre=nombre;
        this.escritorioid=escritorioid;
        this.escritorio=escritorio;
        this.rol=rol;
        this.estado=estado;
        this.tramites=tramites;
        this.secciones=secciones;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscritorio() {
        return escritorio;
    }

    public void setEscritorio(String escritorio) {
        this.escritorio = escritorio;
    }

    public Long getEscritorioid() {
        return escritorioid;
    }

    public void setEscritorioid(Long escritorioid) {
        this.escritorioid = escritorioid;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getSecciones() {
        return secciones;
    }

    public void setSecciones(String secciones) {
        this.secciones = secciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTramites() {
        return tramites;
    }

    public void setTramites(String tramites) {
        this.tramites = tramites;
    }

    public String getToatendidos() {
        return toatendidos;
    }

    public void setToatendidos(String toatendidos) {
        this.toatendidos = toatendidos;
    }

    public String getIsesion() {
        return isesion;
    }

    public void setIsesion(String isesion) {
        this.isesion = isesion;
    }

    public String getIproceso() {
        return iproceso;
    }

    public void setIproceso(String iproceso) {
        this.iproceso = iproceso;
    }
    
}
