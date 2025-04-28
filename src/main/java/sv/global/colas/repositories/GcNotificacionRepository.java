/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import sv.global.colas.entities.GcNotificacion;
import sv.global.colas.entities.GcNotificacionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ever.argueta
 */
public interface GcNotificacionRepository extends CrudRepository<GcNotificacion, GcNotificacionId> {

    @Query("SELECT n FROM GcNotificacion n WHERE n.mEstado = 0 AND n.gcNotificacionId.cUsuario = ?1")
    public List<GcNotificacion> findNotificaciones(String usuario);
}
