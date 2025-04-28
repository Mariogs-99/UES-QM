package sv.global.colas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sv.global.colas.entities.GcPrioridad;

public interface GcPrioridadRepository extends CrudRepository<GcPrioridad, Long> {
	
	@Query("SELECT p FROM GcPrioridad p WHERE ffVigencia > getdate() AND p.nPrioridadId <> 2 ORDER BY p.nPrioridadId ASC ")
	public List<GcPrioridad> getAllPrioridades();
        
        @Query("SELECT p FROM GcPrioridad p WHERE p.bActiva in(1,0) AND p.nPrioridadId = ?1 ")
	public GcPrioridad getPrioridadeById(Long nPrioridadId);
        
        @Query("SELECT count(p) FROM GcPrioridad p WHERE p.bActiva in(1,0) AND REPLACE(upper(p.sNombre),' ','') = REPLACE(upper(?1) ,' ','') and p.nPrioridadId != ?2")
	public Long getPrioridadDuplicada(String nombre,Long id);
	
        @Query(value="select count(N_PRIORIDAD_ID)+1 from gc_prioridad",nativeQuery =true )
        public Long getTotalID();
}
