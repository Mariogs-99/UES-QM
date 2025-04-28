/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcTipoEquipo;

/**
 *
 * @author Owner
 */
public interface GcTipoEquipoRepository extends CrudRepository<GcTipoEquipo,Long>{

        @Query("SELECT t.sNombre FROM GcTipoEquipo t where t.sNombre = ?1 ")
	public String getTipoEquipoByName(String sNombre);
        
        /*Obtener el id a insertar*/
    @Query(value="select count(N_TIPO_ID)+1 from gc_tipo_equipo",nativeQuery = true)
    public Long getTotalId();
}
