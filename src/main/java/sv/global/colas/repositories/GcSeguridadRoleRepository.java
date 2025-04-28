/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcSeguridadRole;

/**
 *
 * @author Ever Argueta
 */
public interface GcSeguridadRoleRepository extends CrudRepository<GcSeguridadRole, Long> {
    @Query(value="select r from GcSeguridadRole r  INNER JOIN r.gcSeguridadUsuarioList u where u.idUsuario=?1")
    public List<GcSeguridadRole> rolesByUsuario(Long idUsuario);
}
