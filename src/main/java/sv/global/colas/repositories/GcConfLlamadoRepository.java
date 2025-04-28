/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sv.global.colas.entities.GcConfLlamado;
import sv.global.colas.entities.GcMultimedia;

/**
 *
 * @author Owner
 */
public interface GcConfLlamadoRepository extends CrudRepository<GcConfLlamado, Long> {
    	
	@Query("SELECT g FROM GcConfLlamado g WHERE g.bActiva=1 ")
	public List<GcConfLlamado> getAllConf();
            	
	@Query("SELECT g FROM GcConfLlamado g WHERE g.bActiva=1 and c_unidad_recep = ?1")
	public List<GcConfLlamado> getConfsByCS(String cunidadRecep);
            	
	@Query("SELECT count(g) FROM GcConfLlamado g WHERE g.bActiva=1 and c_unidad_recep = ?1")
	public int getCounByCS(String cunidadRecep);
        
        //@Query(value="select a.N_MULTIMEDIA_ID||';'||N_ORDEN||';'||S_MULTIMEDIA from GC_MULTIMED_SERVICIO a inner join GC_MULTIMEDIA b on a.N_MULTIMEDIA_ID=b.N_MULTIMEDIA_ID where C_UNIDAD_RECEP=?1 order by N_ORDEN",nativeQuery = true)
        @Query(value="select b.* from GC_MULTIMED_SERVICIO a inner join GC_MULTIMEDIA b on a.N_MULTIMEDIA_ID=b.N_MULTIMEDIA_ID where C_UNIDAD_RECEP=?1 order by N_ORDEN;",nativeQuery = true)
        public List<GcMultimedia> listrepro(String cUnidadRecep);
        
        @Query(value="select count(N_CONFLLAMADO_ID)+1 from gc_conf_llamado",nativeQuery =true)
        public Long getTotalId();
}
