/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcJerarquiaSeccion;

/**
 *
 * @author Owner
 */
public interface GcJerarquiaSeccionRepository extends CrudRepository<GcJerarquiaSeccion,Long> {
    
        @Query("SELECT t FROM GcJerarquiaSeccion t where t.cUnidadRecep = ?1")
	public List<GcJerarquiaSeccion> findAllByUnidad(String cUnidadRecep);
    
        @Query("SELECT t.sUsuario FROM GcJerarquiaSeccion t where C_UNIDAD_RECEP = ?1 and N_SERVICIOS_ID = ?2 and N_JERARQUIA_ID=?3 and N_JRQ_SEC_ID != ?4")
	public String getJerarquiaSecByNameId(String unidadRecep,Long nServiciosId,Long nJerarquiaId,Long nJrqSecId);
    
        @Query("SELECT t.sUsuario FROM GcJerarquiaSeccion t where t.cUnidadRecep=?1 and t.nServiciosId.nServiciosId = ?2 and t.nJerarquiaId.nJerarquiaId=?3")
	public String getJerarquiaSecByName(String unidadRecep,Long nServiciosId,Long nJerarquiaId);
    
        @Query("SELECT max(t.nJrqSecId)+1 FROM GcJerarquiaSeccion t ")
	public Long getMaxJerarquiaSecId();
	
	@Query("SELECT t FROM GcJerarquiaSeccion t where t.cUnidadRecep = ?1 and t.nServiciosId.nServiciosId = ?2 ")
	public List<GcJerarquiaSeccion> getJerarquiaBySeccion(String cUnidadRecep, Long nServiciosId);
    
        @Query("SELECT count(t) FROM GcJerarquiaSeccion t where nJerarquiaId.nJerarquiaId= ?1")
	public Long getRelacionGerarquia(Long nJerarquiaId);
     
        @Query(value = "SELECT * FROM gc_jerarquia_seccion js INNER JOIN gc_tramite tr ON tr.n_servicios_id = js.n_servicios_id INNER JOIN gc_tiquete t ON t.n_tramite_id = tr.n_tramite_id WHERE t.n_tiquete_id = ?1 and js.N_JERARQUIA_ID = ?2" , nativeQuery = true)
        //@Query(value = "SELECT * FROM gc_jerarquia_seccion js INNER JOIN gc_tramite tr ON tr.n_servicios_id = js.n_servicios_id INNER JOIN gc_tiquete t ON t.n_tramite_id = tr.n_tramite_id WHERE t.n_tiquete_id = ?1 and js.N_JERARQUIA_ID = ?2" , nativeQuery = true)
	public GcJerarquiaSeccion getJerarquiaSeccionByTiquete (Long idTiquete,Long nJerarquiaId);  
        
        @Query(value="select count(N_JRQ_SEC_ID)+1 from gc_jerarquia_seccion",nativeQuery = true)
        public Long GetTotalId();
}
