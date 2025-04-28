/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sv.global.colas.entities.GcJerarquia;

/**
 *
 * @author Owner
 */
public interface GcJerarquiaRepository  extends CrudRepository<GcJerarquia,Long>{

        @Query("SELECT t.sJerarquia FROM GcJerarquia t where REPLACE(upper(t.sJerarquia),' ','') = REPLACE(upper(?1) ,' ','')")
	public String getJerarquiaByName(String sJerarquia);
    
        @Query("SELECT t.sJerarquia FROM GcJerarquia t where REPLACE(upper(t.sJerarquia),' ','') = REPLACE(upper(?1) ,' ','') and t.nJerarquiaId != ?2 ")
	public String getJerarquiaByNameId(String sJerarquia,Long nJerarquiaId);
    
        @Query("SELECT max(t.nJerarquiaId)+1 FROM GcJerarquia t ")
	public Long getMaxJerarquiaId();
        
        @Query(value = "SELECT * FROM gc_jerarquia j INNER JOIN gc_jerarquia_seccion js ON js.n_jerarquia_id = j.n_jerarquia_id INNER JOIN gc_tramite tr ON tr.n_servicios_id = js.n_servicios_id INNER JOIN gc_tiquete t ON t.n_tramite_id = tr.n_tramite_id WHERE t.n_tiquete_id = ?1" , nativeQuery = true)
        //@Query(value = "SELECT * FROM gc_jerarquia j INNER JOIN gc_jerarquia_seccion js ON js.n_jerarquia_id = j.n_jerarquia_id INNER JOIN gc_tramite tr ON tr.n_servicios_id = js.n_servicios_id INNER JOIN gc_tiquete t ON t.n_tramite_id = tr.n_tramite_id WHERE t.n_tiquete_id = ?1" , nativeQuery = true)
    public List<GcJerarquia> getJeraruiaByTiqueteId(long idTiquete);
    
    @Query(value="SELECT count(N_JERARQUIA_ID)+1 from gc_jerarquia",nativeQuery = true)
    public Long getTotalId();
    
}
