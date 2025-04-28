/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

import java.util.List;
import sv.global.colas.entities.GcSeguridadRole;
import sv.global.colas.entities.TbUnidadRecep;

/**
 *
 * @author Pino
 */
public class SeguriidadUsuarioPojo {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private short bActiva;
    private String usuario;
    private String disponible;
    private String divRolAsignados;
    private List<GcSeguridadRole> seguridadRoleList;
    private List<GcSeguridadRole> seguridadRoleListAdded;
    private String unidadRecep;
    private TbUnidadRecep tbUnidadRecep;
    private long cambiaPass;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public short getbActiva() {
        return bActiva;
    }

    public void setbActiva(short bActiva) {
        this.bActiva = bActiva;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getDivRolAsignados() {
        return divRolAsignados;
    }

    public void setDivRolAsignados(String divRolAsignados) {
        this.divRolAsignados = divRolAsignados;
    }

    public List<GcSeguridadRole> getSeguridadRoleList() {
        return seguridadRoleList;
    }

    public void setSeguridadRoleList(List<GcSeguridadRole> seguridadRoleList) {
        this.seguridadRoleList = seguridadRoleList;
    }

    public List<GcSeguridadRole> getSeguridadRoleListAdded() {
        return seguridadRoleListAdded;
    }

    public void setSeguridadRoleListAdded(List<GcSeguridadRole> seguridadRoleListAdded) {
        this.seguridadRoleListAdded = seguridadRoleListAdded;
    }

    public String getUnidadRecep() {
        return unidadRecep;
    }

    public void setUnidadRecep(String unidadRecep) {
        this.unidadRecep = unidadRecep;
    }

    public long getCambiaPass() {
        return cambiaPass;
    }

    public void setCambiaPass(long cambiaPass) {
        this.cambiaPass = cambiaPass;
    }

    public TbUnidadRecep getTbUnidadRecep() {
        return tbUnidadRecep;
    }

    public void setTbUnidadRecep(TbUnidadRecep tbUnidadRecep) {
        this.tbUnidadRecep = tbUnidadRecep;
    }
    
}
