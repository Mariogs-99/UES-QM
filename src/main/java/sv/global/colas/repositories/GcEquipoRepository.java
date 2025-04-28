/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcEquipos;

/**
 *
 * @author Owner
 */
public interface GcEquipoRepository extends CrudRepository<GcEquipos, Long>  {
    
        @Query("SELECT t.dEquipo FROM GcEquipos t where t.dEquipo = ?1 ")
	public String getEquipoByName(String dEquipo);
    
        @Query("SELECT t FROM GcEquipos t where c_unidad_recep = ?1 and t.bActiva = 1 ")
	public List<GcEquipos> getEquiposByCS(String unidadRecep);
    
        @Query("SELECT t FROM GcEquipos t where t.bActiva = 1 ")
	public List<GcEquipos> getEquiposActivos();
        
            /*Obtener el id a insertar*/
    @Query(value="select max(N_EQUIPO_ID)+1 from gc_equipos",nativeQuery = true)
    public Long getTotalId();
}
