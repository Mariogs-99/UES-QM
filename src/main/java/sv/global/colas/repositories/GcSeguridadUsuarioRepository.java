/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcSeguridadUsuario;

/**
 *
 * @author Ever Argueta
 */
public interface GcSeguridadUsuarioRepository extends CrudRepository<GcSeguridadUsuario, Long> {

    @Query(value = "select u from GcSeguridadUsuario u where u.cUnidadRecep.cunidadRecep=?1")
    public List<GcSeguridadUsuario> usuariosByCS(String unidad);

    @Query(value = "select u.contrasena from GcSeguridadUsuario u where u.idUsuario=?1")
    public String passById(Long usuarioId);

    @Query(value = "SELECT u.cUnidadRecep.cunidadRecep FROM GcSeguridadUsuario u "
            + "WHERE u.usuario=?1")
    public String encontrarUnidadByUsuario(String usuario);
    
    @Query(value = "SELECT u FROM GcSeguridadUsuario u "
            + "WHERE u.usuario=?1")
    public GcSeguridadUsuario  fingByUsuario(String usuario);

    @Query(value = "SELECT MAX(g.idUsuario) AS ID_USUARIO FROM GcSeguridadUsuario g")
    public Long obtenerUltimoId();
}
